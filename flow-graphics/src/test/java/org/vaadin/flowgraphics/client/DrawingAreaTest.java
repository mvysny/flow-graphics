package org.vaadin.flowgraphics.client;

import org.junit.jupiter.api.Test;
import org.vaadin.flowgraphics.client.shape.Circle;

import static org.junit.jupiter.api.Assertions.*;

class DrawingAreaTest {
    @Test
    public void smoke() {
        new DrawingArea(100, 100).flush();
    }

    @Test
    public void simpleScenario() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        Circle circle = new Circle(100, 100, 50);
        circle.setFillColor("red");
        canvas.add(circle);

        assertEquals("<svg overflow=\"hidden\" width=\"100\" height=\"100\">\n" +
                " <defs></defs><circle fill=\"red\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" r=\"50\" cx=\"100\" cy=\"100\"></circle>\n" +
                "</svg>", canvas.getElement().getProperty("innerHTML"));
    }
}