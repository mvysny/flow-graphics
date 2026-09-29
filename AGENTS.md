# Flow Graphics

## What this is

A cross-browser vector graphics library for Vaadin Flow.

A drop-in replacement for the [GWT Graphics](https://vaadin.com/directory/component/gwt-graphics)
add-on, but works with Vaadin 23+.

## Promises

- **Drop-in for GWT Graphics.** Same class names and API as the GWT add-on, so porting a component is an import rename plus `Div` for `Widget`.
- **Emulate GWT Graphics, don't improve on it.** Behaviour matches the original, its bugs included; where the server side can't match, mark the gap `@todo mavi` rather than invent new semantics.
- **Side-by-side with GWT Graphics.** Everything lives under `org.vaadin.flowgraphics`, never `org.vaadin.gwtgraphics`, so one classpath carries both during a gradual migration.
- **Vaadin 23+ on Java 11.** The library jar is Java 11 bytecode and `compileOnly` on Vaadin, so it runs on whatever Flow version the app brings.

## Design docs

| File | Owns | Loaded |
|---|---|---|
| `README.md` | the pitch, install, migration steps | — |
| `CONTRIBUTING.md` | the release procedure | — |
| `AGENTS.md` (this) | promises, invariants, the module map, conventions, commands | every turn |
| `design/architecture.md` | how the pieces compose — the jsoup SVG tree, the flush, the GWT shims; normative | lazy |
| `design/decisions.md` | why this and not that — `D_` entries, FAQ-shaped | lazy |
| doc comments | what one symbol does and why it is shaped so | at the symbol |

Every fact lives in exactly one of these; the others link to it.

## Invariants

- **The browser sees the SVG only when `DrawingArea.flush()` serialises it.** Every mutator ends in `changed()` / `flushLazy()`, which schedules it; a raw jsoup edit needs its own `flushLazy()`. The flow is in architecture.md. See `D_jsoup_svg_tree`, `D_flush_per_mutation`.
- **A drawn object is mutated with the UI lock held.** `flushLazy()` throws `IllegalStateException` from a background thread; use `ui.access()`.
- **Nothing reads browser-side geometry.** `SVGUtil.getBBBox` returns zeros on the server; code needing bbox or text metrics does not work, and is marked `@todo mavi`.

## Module map

- `flow-graphics` — the published library: the drawing area, shapes, paths, groups, SVG rendering.
- `flow-graphics/…/client/gwt` — minimal stand-ins for the GWT classes the ported code calls.
- `demo-app` — a Vaadin Boot app drawing a few shapes; not published.

## Conventions

- **Java 11, no Kotlin, no frontend code in the library.** Vaadin is `compileOnly`; the only DOM is jsoup's.
- **Ported files keep Henri Kerola's Apache 2.0 header and GWT-era structure.** Files added here have no header.
- **Server-side gaps are marked `// @todo mavi <why>`** at the site, not worked around silently.
- **Tests: JUnit 5 + Karibu-Testing, asserting the serialised `innerHTML`.** `MockVaadin.clientRoundtrip()` triggers the flush.
- **Releases from JDK 11 only**; `build.gradle.kts` refuses `publish` on a newer JDK. Procedure in `CONTRIBUTING.md`.

## Commands

- `./gradlew test` — all tests.
- `./gradlew check` — tests, then `design/verify_design_tripwires.sh`.
- `./gradlew` — `clean build`; what CI runs on push on JDK 11, 17 and 21 (`.github/workflows/gradle.yml`), beside a job of its own for the tripwires.
- `./gradlew :demo-app:run` — the demo on http://localhost:8080.

## Skills this project follows

- **Karibu-Testing:** browserless Vaadin tests via `MockVaadin` and `LocatorJ`, no Selenium; the `karibu-testing` skill has the helpers.

## Maintenance of this file

Loaded every turn; cap 34 KB, a module's own `AGENTS.md` 10 KB. Over it, in this order:
delete what has no home — status, history, class lists, what the code already says; trim
each line to its fact plus one clause and send the explanation home — why →
`design/decisions.md`, how across symbols → `design/architecture.md`, how in one symbol →
its doc comment, what upstream does → `design/research.md`; only then a module's own
`AGENTS.md`, peripheral modules first, never the core. Never paraphrase a lazy entry into a
line here. `design/verify_design_tripwires.sh` checks the caps and the cites.
