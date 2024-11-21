package org.vaadin.flowgraphics.client.gwt;

public class DeferredCommand {
    public static void addCommand(Command cmd) {
        cmd.execute();
    }
}
