package org.vaadin.gwtgraphics.client.gwt;

import org.jsoup.nodes.Element;

public abstract class AbstractWidget implements Widget {
    private Element element;

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    private boolean isAttached = false;

    protected void onAttach() {
        isAttached = true;
    }
    protected void onDetach() {
        isAttached = false;
    }
    public boolean isAttachedGWT() {
        return isAttached;
    }
    public boolean isAttached() {
        return isAttachedGWT();
    }
    protected void doAttachChildren() {}
    protected void doDetachChildren() {}

    public void setStyleName(String style) {
        element.attr("class", style);
    }

    public void setHeight(String height) {
        element.attr("height", height);
    }
    public void setWidth(String width) {
        element.attr("width", width);
    }
}
