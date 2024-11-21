# Flow Graphics

A cross-browser vector graphics library for Vaadin Flow.

A drop-in replacement for the [GWT Graphics](https://vaadin.com/directory/component/gwt-graphics)
add-on, but works with Vaadin 23+.

Designed to be used side-by-side with the old GWT Graphics add-on, so that your
project can be migrated gradually. Instead of the `org.vaadin.flowgraphics` package,
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

## Migrating your project from GWT Graphics to Flow Graphics

TODO
