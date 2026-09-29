# Flush on every mutation, not only on add/remove

## The bug

`DrawingArea.flushLazy()` is called only by `DrawingArea.add`, `insert`, `bringToFront` and
`remove`. Nothing else schedules a flush, so a change to an object that is already drawn stays in
the server-side jsoup tree and never reaches the browser:

```java
Circle circle = new Circle(100, 100, 50);
canvas.add(circle);          // flushes: a white circle, the default fill
circle.setFillColor("red");  // never sent, until an unrelated add/remove flushes
```

It also affects:

- every setter on `Shape`, `Line`, `Image`, `Text`, `Rectangle`, `Circle`, `Ellipse`, and
  `VectorObject.setRotation` / `setStyleName`;
- `Path` step methods (`lineTo`, `moveTo`, …) on a drawn path;
- `Group.add` / `insert` / `remove` / `bringToFront` / `clear`: none of them flush, so a shape
  added to a group that is already on the canvas doesn't show up either.

The existing tests don't catch it: `DrawingAreaTest` sets every attribute *before* `canvas.add`.

GWT Graphics had no flush at all. Each setter wrote straight to the live browser DOM
(`SVGUtil.setAttributeNS` is JSNI on a `com.google.gwt.dom.client.Element`; checked against
`henrikerola/gwt-graphics` @ `4d06582`, where the word "flush" appears nowhere). The partial
flush came in with this port, in `39b6155` "DrawingArea: auto-flush". So a ported app that
changes colours or positions after drawing (hover highlights, a moving marker) breaks silently.
That goes against the **Drop-in for GWT Graphics** promise.

## Proposal

One chokepoint on `VectorObject`, say `protected void changed()`, that walks `getParent()` up to
the `DrawingArea` and calls `flushLazy()` on it. It does nothing if the object isn't under a
drawing area yet, because `add` flushes anyway when it is attached. Every mutator ends with
`changed()`; `Group`'s container methods too.

`flushLazy()` already dedupes (one `beforeClientResponse` registration per response), so a loop
of 1000 setters still costs one serialisation.

## Alternatives

- **Always re-flush on every response while attached.** `flush()` would re-register itself.
  Nothing to forget in the setters, but it resends the whole SVG on every round trip, including
  ones that have nothing to do with the canvas. This depends on `Q_flow_skips_equal_property`.
- **Make it the caller's job:** document "call `canvas.flushLazy()` after changing a drawn
  object". No code to write, but it breaks drop-in: every ported GWT component needs to be
  audited for mutations.
- **Dirty flag per object, checked in `flush`:** doesn't help, since the problem is that no
  flush is scheduled at all, not that flush does too much.

## Open questions

- `Q_parent_walk`: `getParent()` returns `Widget`, and the chain is `VectorObject` → `Group`* →
  `DrawingArea`. Walk it with `instanceof`, or cache the owning `DrawingArea` in `setParent`
  (and clear it on detach)? Caching is O(1), but it has to be propagated to a group's children
  when the group is re-parented.
- `Q_ui_lock` (resolved): the behaviour change is acceptable, since `add` / `insert` /
  `bringToFront` / `remove` already NPE off the UI thread and this only extends that rule to
  setters. `flushLazy()` checks `UI.getCurrent()` and throws `IllegalStateException` with a
  message that tells the caller to wrap the change in `ui.access()`, so there's no bare NPE. The
  check covers only the first mutation of a response (the `flushRegistration == null` guard), so
  a background write racing an already-scheduled flush goes unnoticed. That's deliberate: a
  per-call `VaadinSession.hasLock()` check is more than Flow does for its own components, and the
  `AGENTS.md` invariant already states the rule. A detached canvas built on a background thread
  now throws on setters too, not just on `add`, so build it inside `ui.access()`.
- `Q_flow_skips_equal_property`: does Flow skip sending `innerHTML` when the new value equals
  the old one? If yes, the always-re-flush alternative is cheaper than it looks. Verify in
  flow-server's `ElementPropertyMap` before relying on it.
- `Q_mutating_getSvgElement`: someone who edits `getSvgElement()` directly still has to call
  `flushLazy()` themselves (its doc comment says so). Keep it that way?

## Test to add

A Karibu test in `DrawingAreaTest`: `canvas.add(circle)`, `clientRoundtrip()`, then
`circle.setFillColor("red")`, `clientRoundtrip()`, and assert `innerHTML` has `fill="red"`. Plus
the same through a `Group` that is already on the canvas. Also a test that calls
`circle.setFillColor` on a drawn circle from a plain thread and expects the
`IllegalStateException` (`Q_ui_lock`).

## On graduation

- `AGENTS.md`'s first invariant becomes a rule the code actually follows ("every mutator ends
  in `changed()`…"), not just a description of how it works now.
- The "Drawing a shape" flow in `design/architecture.md` gets the setter-after-add step.
- `changed()`'s doc comment carries the parent-walk mechanism.
