# Architecture

How the pieces compose — what no single symbol can say and what would be expensive to overturn:
wiring and dependency direction, the lifecycle / threading / data-flow story, the flows a newcomer
needs, where to start reading. **Normative: the code conforms.** Change this file first, then the
code. Not here: why (`decisions.md` — cite the `D_`), one symbol's behaviour (its doc comment),
the module map (`AGENTS.md`). Only the sections with content; the worked example in each is the
ruler. Cap 12 KB — over it, research or doc-comment content has crept in.

---

## Wiring

- `DrawingArea` is the only Flow component: a `Div` holding one jsoup `<svg>` root. Shapes, `Line`, `Image`, `Text` and `Group` are plain Java objects over a jsoup `Element`, never Flow components (`D_jsoup_svg_tree`).
- Every attribute write goes through `SVGImpl` (one static instance per class) and `SVGUtil`; shapes never touch their `Element` directly.
- Every mutator (each setter, `Path`'s steps, `Group`'s container methods, `DrawingArea`'s size) ends by scheduling the owning canvas's flush: `VectorObject.changed()` for a drawn object, `flushLazy()` for the canvas itself. A raw jsoup edit through `getSvgElement()` or `getElement()` has to call `flushLazy()` itself (`D_flush_per_mutation`).
- `client.gwt` stands in for the GWT API the ported code calls — `AbstractWidget`'s attach flag, `Styles` over the `style` attribute, `DeferredCommand` running inline, `Animation` doing nothing. Nothing in it references Flow.
- Attach state mirrors Flow's: `DrawingArea.onAttach/onDetach` walk the children and call `VectorObject.onAttach/onDetach`, which is what `isAttached()` reports.

## Flows

**Drawing a shape** (request thread, UI lock held):

1. `new Circle(…)` → `VectorObject` asks `SVGImpl.createElement` for a detached `<circle>`; `Shape` sets the default fill and stroke.
2. `canvas.add(circle)` → `SVGImpl.add` appends the element under the `<svg>` root; `setParent` fires `onAttach` if the canvas is attached.
3. `add` calls `flushLazy()`, which registers `flush()` once via `UI.beforeClientResponse`.
4. Before the response, `flush()` sets the `Div`'s `innerHTML` to the serialised `<svg>` and clears the registration.
5. The client replaces the `Div`'s content wholesale; no per-shape state exists on the client.
6. A later `circle.setFillColor("red")`, or any other mutator, ends in `changed()`. It walks `getParent()` through any `Group`s up to the canvas and calls `flushLazy()`, and steps 4–5 repeat.

## Where to start reading

`DrawingArea` — the container, the attach walk and the flush, in one file; then `SVGImpl` for how each object maps to SVG attributes.
