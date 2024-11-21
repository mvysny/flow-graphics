package org.vaadin.flowgraphics.client.gwt;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StylesTest {
    @Test
    public void empty() {
        assertEquals(0, new Styles(new Element("div")).getStyles().size());
    }

    @Test
    public void parseStyles() {
        final Element div = new Element("div");
        final Styles styles = new Styles(div);
        div.attr("style", "width: 100%; height: 100px ");
        assertEquals("100%", styles.getProperty("width"));
        assertEquals("100px", styles.getProperty("height"));
    }
}