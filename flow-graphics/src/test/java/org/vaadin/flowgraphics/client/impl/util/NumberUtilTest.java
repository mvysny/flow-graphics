package org.vaadin.flowgraphics.client.impl.util;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilTest {
    @Test
    public void parseIntValue() {
        assertEquals(12, NumberUtil.parseIntValue("12", -1));
        assertEquals(12, NumberUtil.parseIntValue("12px", -1));
        assertEquals(-1, NumberUtil.parseIntValue(null, -1));
        assertEquals(-1, NumberUtil.parseIntValue("", -1));
        assertEquals(-1, NumberUtil.parseIntValue("1.5", -1));
        assertEquals(-1, NumberUtil.parseIntValue("abc", -1));
    }

    @Test
    public void parseIntAttribute() {
        final Element element = new Element("rect").attr("width", "30px");
        assertEquals(30, NumberUtil.parseIntValue(element, "width", -1));
        assertEquals(-1, NumberUtil.parseIntValue(element, "height", -1));
    }

    @Test
    public void parseDoubleValue() {
        assertEquals(0.5, NumberUtil.parseDoubleValue("0.5", -1));
        assertEquals(2.5, NumberUtil.parseDoubleValue("2.5px", -1));
        assertEquals(-1, NumberUtil.parseDoubleValue(null, -1));
        assertEquals(-1, NumberUtil.parseDoubleValue("", -1));
        assertEquals(-1, NumberUtil.parseDoubleValue("abc", -1));
    }
}
