# Decisions

Why this project is the way it is and not otherwise — FAQ-shaped: each entry is a question and
its current answer. Rewrite the answer when it changes; delete the entry when nobody asks any
more. An entry is earned by what it would cost to reverse — half the code base — or by research
the next person would otherwise redo (cited as its `R_`). Not an entry: windows → panels
"because that's the trend", this red over that red, `get_foo` over `is_foo?`, the testing library,
the CI host, a version bump — a comment at the site of the choice, or nothing; nothing about
`design/` itself. Cite by slug, `D_<slug>`, never by position; `grep '^## D_' design/decisions.md`
is the index. The first entry is the ruler: every later one trims to its length — which is how
long this file gets, so keep it short. When you have written an entry, re-read it against the one
above, open the doc comments it touches and cut what they already say, then cut the fat.

---

## D_jsoup_svg_tree — Why build the SVG as a server-side jsoup tree rather than a client-side component?

**Drop-in for GWT Graphics** means the ported `SVGImpl` should stay the GWT original with one
type swapped: GWT's DOM `Element` became jsoup's `Element`, which has the same attribute and
child operations, and ships inside Flow already. Each `DrawingArea` owns one jsoup `<svg>` tree;
before the response, `flush()` writes the whole tree as the `innerHTML` of a plain `Div`. The
library is then a jar of server-side Java — no npm package, no web component, nothing in the
app's frontend build, which is what lets it follow any Vaadin 23+. Why not a Lit/TypeScript
component mirroring the object model: every shape and setter would need a JS twin and a sync
protocol, a second codebase for an add-on whose value is that migration is an import rename.
Why not one Flow `Element` per shape: it rewrites every line of `SVGImpl` and spends a state
node per circle for pictures that are usually redrawn whole. The cost we carry: every flush
resends the entire SVG; nothing measured in the browser — `getBBox`, text size — is known on
the server; per-shape click handlers and GWT `Animation` are gone, the latter a no-op shim.

## D_flush_per_mutation — Why does every mutator schedule a flush, rather than the canvas resending on every response or the caller flushing?

GWT Graphics had no flush. Each setter wrote to the live browser DOM, so a colour changed after
drawing showed up at once, and **Emulate GWT Graphics** means a ported hover highlight has to
keep working. So every mutator ends in `changed()` (or `flushLazy()` on the canvas). One
registration per response keeps a loop of 1000 setters at one serialisation. Why not re-flush on
every response while attached: Flow never dedupes `innerHTML` (see `flush()`), so every round
trip, including ones unrelated to the canvas, would carry the whole SVG. Why not make the caller
flush: every ported component would need an audit for mutations, which breaks drop-in. The cost
we carry: a raw jsoup edit through `getSvgElement()` or `getElement()` still needs a manual
`flushLazy()`. jsoup has no mutation events, so that gap is marked `@todo mavi`, not closed.
