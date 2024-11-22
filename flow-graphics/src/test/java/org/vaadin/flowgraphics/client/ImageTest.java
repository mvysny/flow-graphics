package org.vaadin.flowgraphics.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    @Test public void href() {
        final Image image = new Image(0, 0, 10, 10, "https://foo.bar");
        assertEquals("<image preserveaspectratio=\"none\" x=\"0\" y=\"0\" width=\"10\" height=\"10\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xlink:href=\"https://foo.bar\"></image>", image.getElement().toString());
    }
}