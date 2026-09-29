package org.vaadin.flowgraphics.client.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    @Test
    public void getters() {
        final Circle circle = new Circle(5, 6, 7);
        assertEquals(5, circle.getX());
        assertEquals(6, circle.getY());
        assertEquals(7, circle.getRadius());
    }

    @Test
    public void setPropertyDouble() {
        final Circle circle = new Circle(0, 0, 1);
        circle.setPropertyDouble("Radius", 9.5);
        circle.setPropertyDouble("fillOpacity", 0.5);
        circle.setPropertyDouble("strokeOpacity", 0.25);
        circle.setPropertyDouble("strokeWidth", 4);
        assertEquals(9, circle.getRadius());
        assertEquals(0.5, circle.getFillOpacity());
        assertEquals(0.25, circle.getStrokeOpacity());
        assertEquals(4, circle.getStrokeWidth());
    }
}
