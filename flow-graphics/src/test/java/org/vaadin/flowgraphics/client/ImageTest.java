package org.vaadin.flowgraphics.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    @Test public void href() {
        final Image image = new Image(0, 0, 10, 10, "https://foo.bar");
        assertEquals("<image preserveaspectratio=\"none\" x=\"0\" y=\"0\" width=\"10\" height=\"10\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:href=\"https://foo.bar\"></image>", image.getElement().toString());
    }

    @Test public void getters() {
        final Image image = new Image(1, 2, 3, 4, "https://foo.bar");
        assertEquals(1, image.getX());
        assertEquals(2, image.getY());
        assertEquals(3, image.getWidth());
        assertEquals(4, image.getHeight());
        assertEquals("https://foo.bar", image.getHref());
    }

    @Test public void setters() {
        final Image image = new Image(0, 0, 10, 10, "https://foo.bar");
        image.setX(5);
        image.setY(6);
        image.setWidth(7);
        image.setHeight(8);
        image.setHref("https://baz");
        assertEquals("<image preserveaspectratio=\"none\" x=\"5\" y=\"6\" width=\"7\" height=\"8\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:href=\"https://baz\"></image>", image.getElement().toString());
        assertEquals("https://baz", image.getHref());
    }

    @Test public void pixelSizeStrings() {
        final Image image = new Image(0, 0, 10, 10, "https://foo.bar");
        image.setWidth("50px");
        image.setHeight("60px");
        assertEquals(50, image.getWidth());
        assertEquals(60, image.getHeight());
    }

    @Test public void nonPixelSizeStringsFail() {
        final Image image = new Image(0, 0, 10, 10, "https://foo.bar");
        assertThrows(IllegalArgumentException.class, () -> image.setWidth("50%"));
        assertThrows(IllegalArgumentException.class, () -> image.setHeight("5em"));
        assertThrows(IllegalArgumentException.class, () -> image.setWidth("abcpx"));
        assertThrows(IllegalArgumentException.class, () -> image.setHeight(null));
    }

    @Test public void setPropertyDouble() {
        final Image image = new Image(0, 0, 10, 10, "https://foo.bar");
        image.setPropertyDouble("X", 1.9);
        image.setPropertyDouble("y", 2);
        image.setPropertyDouble("width", 3);
        image.setPropertyDouble("height", 4);
        image.setPropertyDouble("unknown", 99);
        assertEquals(1, image.getX());
        assertEquals(2, image.getY());
        assertEquals(3, image.getWidth());
        assertEquals(4, image.getHeight());
    }

    @Test public void rotationPivotsOnOrigin() {
        // the pivot should be the bbox centre, which the server can't measure:
        // see the "Nothing reads browser-side geometry" invariant.
        final Image image = new Image(10, 10, 20, 20, "https://foo.bar");
        image.setPropertyDouble("rotation", 45);
        assertEquals("rotate(45 0 0)", image.getElement().attr("transform"));
        image.setRotation(0);
        assertEquals("", image.getElement().attr("transform"));
    }
}
