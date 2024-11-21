package org.vaadin.flowgraphics.client.gwt;

import org.jsoup.nodes.Element;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Styles implements Serializable {
    private final Element element;

    public Styles(Element element) {
        this.element = Objects.requireNonNull(element);
    }

    public Map<String, String> getStyles() {
        final String[] styles = element.attr("style").split(";");
        final HashMap<String, String> result = new HashMap<>();
        for (String style : styles) {
            style = style.trim();
            if (style.isEmpty()) {
                continue;
            }
            final String[] split = style.split(":");
            if (split.length == 2) {
                result.put(split[0].trim(), split[1].trim());
            }
        }
        return result;
    }

    private void setStyles(Map<String, String> styles) {
        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : styles.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        element.attr("style", sb.toString());
    }

    public void setPropertyPx(String propertyName, int value) {
        setProperty(propertyName, value + "px");
    }

    public void setProperty(String propertyName, String value) {
        final Map<String, String> styles = getStyles();
        styles.put(propertyName, value);
        setStyles(styles);
    }

    public String getProperty(String propertyName) {
        return getStyles().get(propertyName);
    }
}
