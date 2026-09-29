package org.vaadin.flowgraphics.client.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    @Test
    public void rendering() {
        final Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertEquals("<rect fill=\"white\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" x=\"10\" y=\"20\" width=\"30\" height=\"40\"></rect>", rect.getElement().toString());
    }

    @Test
    public void getters() {
        final Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertEquals(10, rect.getX());
        assertEquals(20, rect.getY());
        assertEquals(30, rect.getWidth());
        assertEquals(40, rect.getHeight());
        assertEquals(0, rect.getRoundedCorners());
    }

    @Test
    public void roundedCorners() {
        final Rectangle rect = new Rectangle(0, 0, 10, 10);
        rect.setRoundedCorners(5);
        assertEquals(5, rect.getRoundedCorners());
        assertEquals("5", rect.getElement().attr("rx"));
        assertEquals("5", rect.getElement().attr("ry"));

        rect.setRoundedCorners(-3);
        assertEquals(0, rect.getRoundedCorners());
    }

    @Test
    public void pixelSizeStrings() {
        final Rectangle rect = new Rectangle(0, 0, 10, 10);
        rect.setWidth("50px");
        rect.setHeight("60px");
        assertEquals(50, rect.getWidth());
        assertEquals(60, rect.getHeight());
    }

    @Test
    public void nonPixelSizeStringsFail() {
        final Rectangle rect = new Rectangle(0, 0, 10, 10);
        assertThrows(IllegalArgumentException.class, () -> rect.setWidth("50%"));
        assertThrows(IllegalArgumentException.class, () -> rect.setHeight("5em"));
        assertThrows(IllegalArgumentException.class, () -> rect.setWidth("abcpx"));
        assertThrows(IllegalArgumentException.class, () -> rect.setHeight(null));
    }

    @Test
    public void setPropertyDouble() {
        final Rectangle rect = new Rectangle(0, 0, 10, 10);
        rect.setPropertyDouble("width", 15.7);
        rect.setPropertyDouble("height", 25);
        rect.setPropertyDouble("roundedCorners", 3);
        rect.setPropertyDouble("x", 7);
        assertEquals(15, rect.getWidth());
        assertEquals(25, rect.getHeight());
        assertEquals(3, rect.getRoundedCorners());
        assertEquals(7, rect.getX());
    }

    @Test
    public void fillAndStroke() {
        final Rectangle rect = new Rectangle(0, 0, 10, 10);
        rect.setFillColor("#f00");
        rect.setFillOpacity(0.5);
        rect.setStrokeColor("blue");
        rect.setStrokeOpacity(0.25);
        rect.setStrokeWidth(3);
        assertEquals("#f00", rect.getFillColor());
        assertEquals(0.5, rect.getFillOpacity());
        assertEquals("blue", rect.getStrokeColor());
        assertEquals(0.25, rect.getStrokeOpacity());
        assertEquals(3, rect.getStrokeWidth());
    }

    @Test
    public void nullFillDisablesFilling() {
        final Rectangle rect = new Rectangle(0, 0, 10, 10);
        rect.setFillColor(null);
        assertEquals("none", rect.getElement().attr("fill"));
        assertNull(rect.getFillColor());
    }

    @Test
    public void styleName() {
        final Rectangle rect = new Rectangle(0, 0, 10, 10);
        rect.setStyleName("box");
        assertEquals("box-svg", rect.getElement().attr("class"));
    }
}
