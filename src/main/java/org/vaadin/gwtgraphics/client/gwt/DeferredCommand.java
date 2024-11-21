package org.vaadin.gwtgraphics.client.gwt;

public class DeferredCommand {
    public static void addCommand(Command cmd) {
        cmd.execute();
    }
}
