# Flow Graphics

A cross-browser vector graphics library for Vaadin Flow.

A drop-in replacement for the [GWT Graphics](https://vaadin.com/directory/component/gwt-graphics)
add-on, but works with Vaadin 23+.

Designed to be used side-by-side with the old GWT Graphics add-on, so that your
project can be migrated gradually. Instead of the `org.vaadin.gwtgraphics` package,
classes are stored in the `org.vaadin.flowgraphics` package.

## About

The goal of the GWT Graphics library is to provide consistent cross-browser vector graphics library
for Vaadin Flow. Under the hood, the library renders to SVG.

Sample code
```java
// After the above steps, you can use Flow Graphics in your project. 
// To draw something with the library, you have to add an instance of 
// DrawingArea to your application:
DrawingArea canvas = new DrawingArea(400, 400);
panel.add(canvas);

// After that you can start drawing. 
// For example, the following code draws a red circle :
Circle circle = new Circle(100, 100, 50);
circle.setFillColor("red");
canvas.add(circle);
```

## Demo

The demo app is available; open the `Main` class located in the `demo-app` module, and run the `main()` method.

## Migrating your project from GWT Graphics to Flow Graphics

Add a dependency on this library to your project:
```groovy
repositories {
    mavenCentral()
}
dependencies {
    compile("com.github.mvysny.flow-graphics:flow-graphics:1.0.0")
}
```
> Note: obtain the newest version from the tag name at the top of the page

Maven: (it's very simple since jdbi-orm is in Maven Central):

```xml
<project>
	<dependencies>
		<dependency>
			<groupId>com.github.mvysny.flow-graphics</groupId>
			<artifactId>flow-graphics</artifactId>
			<version>1.0.0</version>
		</dependency>
    </dependencies>
</project>
```

Now, you can rewrite your GWT component in-place, or copy to a new class, e.g. `MyComponentFlow`:

1. Make the component extend Flow `Div` class instead of a GWT `Widget`
    * Alternatively you can extend the `DrawingArea` directly, since `DrawingArea` is a Flow component now.
2. Replace all `org.vaadin.gwtgraphics.*` imports with `org.vaadin.flowgraphics.*` imports.
3. The `DrawingArea` is now a Flow component, you can just place it anywhere within the Flow component tree
