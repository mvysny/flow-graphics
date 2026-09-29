package org.vaadin.flowgraphics.client.gwt;

public abstract class Animation {
    protected abstract void onUpdate(double progress);
    protected void onComplete() {}
    public void cancel() {}
    // @todo mavi no animation frames on the server: onUpdate() and onComplete() never fire
    public void run(int duration) {}
}
