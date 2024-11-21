package org.vaadin.flowgraphics.client.gwt;

public abstract class Animation {
    protected abstract void onUpdate(double progress);
    protected void onComplete() {}
    public void cancel() {}
    public void run(int duration) {}
}
