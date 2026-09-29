package org.vaadin.flowgraphics.client.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {
    @Test
    public void rendering() {
        final Text text = new Text(10, 20, "Hello");
        assertEquals("<text text-anchor=\"start\" fill=\"white\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" x=\"10\" y=\"20\" font-family=\"Arial\" font-size=\"20\">\n Hello\n</text>", text.getElement().toString());
    }

    @Test
    public void getters() {
        final Text text = new Text(10, 20, "Hello");
        assertEquals(10, text.getX());
        assertEquals(20, text.getY());
        assertEquals("Hello", text.getText());
        assertEquals("Arial", text.getFontFamily());
        assertEquals(20, text.getFontSize());
    }

    @Test
    public void setters() {
        final Text text = new Text(0, 0, "Hello");
        text.setText("World");
        text.setFontFamily("Courier");
        text.setFontSize(12);
        assertEquals("World", text.getText());
        assertEquals("Courier", text.getFontFamily());
        assertEquals(12, text.getFontSize());
    }

    @Test
    public void textIsEscaped() {
        final Text text = new Text(0, 0, "<b>a & b</b>");
        assertEquals("<b>a & b</b>", text.getText());
        assertTrue(text.getElement().toString().contains("&lt;b&gt;a &amp; b&lt;/b&gt;"), text.getElement().toString());
    }

    @Test
    public void setPropertyDouble() {
        final Text text = new Text(0, 0, "Hello");
        text.setPropertyDouble("fontSize", 14.2);
        text.setPropertyDouble("x", 3);
        assertEquals(14, text.getFontSize());
        assertEquals(3, text.getX());
    }

    @Test
    public void textMetricsAreZeroOnServer() {
        // text metrics need the browser, see the "Nothing reads browser-side geometry" invariant.
        final Text text = new Text(0, 0, "Hello");
        assertEquals(0, text.getTextWidth());
        assertEquals(0, text.getTextHeight());
    }
}
