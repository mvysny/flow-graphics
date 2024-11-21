package demoapp;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainRoute extends VerticalLayout {
    public MainRoute() {
        add(new H1("Hello World"));
    }
}
