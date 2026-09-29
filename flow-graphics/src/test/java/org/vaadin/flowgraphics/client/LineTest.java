package org.vaadin.flowgraphics.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    @Test
    public void rendering() {
        final Line line = new Line(1, 2, 3, 4);
        assertEquals("<line x1=\"1\" y1=\"2\" x2=\"3\" y2=\"4\" stroke-width=\"1\" stroke-opacity=\"1.0\" stroke=\"black\"></line>", line.getElement().toString());
    }

    @Test
    public void getters() {
        final Line line = new Line(1, 2, 3, 4);
        assertEquals(1, line.getX1());
        assertEquals(2, line.getY1());
        assertEquals(3, line.getX2());
        assertEquals(4, line.getY2());
        assertEquals("black", line.getStrokeColor());
        assertEquals(1, line.getStrokeWidth());
        assertEquals(1.0, line.getStrokeOpacity());
        assertEquals(0, line.getRotation());
    }

    @Test
    public void setters() {
        final Line line = new Line(0, 0, 0, 0);
        line.setX1(5);
        line.setY1(6);
        line.setX2(7);
        line.setY2(8);
        line.setStrokeColor("red");
        line.setStrokeWidth(2);
        line.setStrokeOpacity(0.5);
        assertEquals("<line x1=\"5\" y1=\"6\" x2=\"7\" y2=\"8\" stroke-width=\"2\" stroke-opacity=\"0.5\" stroke=\"red\"></line>", line.getElement().toString());
    }

    @Test
    public void setPropertyDouble() {
        final Line line = new Line(0, 0, 0, 0);
        line.setPropertyDouble("x1", 1.9);
        line.setPropertyDouble("Y1", 2);
        line.setPropertyDouble("x2", 3);
        line.setPropertyDouble("y2", 4);
        line.setPropertyDouble("strokeOpacity", 0.75);
        line.setPropertyDouble("strokeWidth", 5);
        line.setPropertyDouble("unknown", 99);
        assertEquals(1, line.getX1());
        assertEquals(2, line.getY1());
        assertEquals(3, line.getX2());
        assertEquals(4, line.getY2());
        assertEquals(0.75, line.getStrokeOpacity());
        assertEquals(5, line.getStrokeWidth());
    }

    @Test
    public void zeroRotationClearsTransform() {
        final Line line = new Line(0, 0, 10, 10);
        line.setRotation(0);
        assertEquals("", line.getElement().attr("transform"));
    }
}
