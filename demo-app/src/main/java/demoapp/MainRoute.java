package demoapp;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.flowgraphics.client.DrawingArea;
import org.vaadin.flowgraphics.client.shape.Circle;
import org.vaadin.flowgraphics.client.shape.Rectangle;

@Route("")
public class MainRoute extends VerticalLayout {
    public MainRoute() {
        add(new H1("Hello World"));

        final DrawingArea canvas = new DrawingArea(200, 200);
        Circle circle = new Circle(100, 100, 50);
        circle.setFillColor("red");
        canvas.add(circle);
        final Rectangle rect = new Rectangle(0, 0, 100, 100);
        rect.setFillColor("blue");
        canvas.add(rect);

        add(canvas);
    }
}
