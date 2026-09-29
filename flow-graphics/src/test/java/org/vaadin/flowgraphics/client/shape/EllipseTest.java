package org.vaadin.flowgraphics.client.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EllipseTest {
    @Test
    public void rendering() {
        final Ellipse ellipse = new Ellipse(50, 60, 20, 10);
        assertEquals("<ellipse fill=\"white\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" rx=\"20\" ry=\"10\" cx=\"50\" cy=\"60\"></ellipse>", ellipse.getElement().toString());
    }

    @Test
    public void getters() {
        final Ellipse ellipse = new Ellipse(50, 60, 20, 10);
        assertEquals(50, ellipse.getX());
        assertEquals(60, ellipse.getY());
        assertEquals(20, ellipse.getRadiusX());
        assertEquals(10, ellipse.getRadiusY());
    }

    @Test
    public void setPropertyDouble() {
        final Ellipse ellipse = new Ellipse(0, 0, 1, 1);
        ellipse.setPropertyDouble("radiusX", 8.9);
        ellipse.setPropertyDouble("RADIUSY", 4);
        ellipse.setPropertyDouble("y", 3);
        assertEquals(8, ellipse.getRadiusX());
        assertEquals(4, ellipse.getRadiusY());
        assertEquals(3, ellipse.getY());
    }

    @Test
    public void sizeStringsUnsupported() {
        final Ellipse ellipse = new Ellipse(0, 0, 1, 1);
        assertThrows(UnsupportedOperationException.class, () -> ellipse.setWidth("10px"));
        assertThrows(UnsupportedOperationException.class, () -> ellipse.setHeight("10px"));
    }
}
