package org.vaadin.flowgraphics.client;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.vaadin.flow.component.UI;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vaadin.flowgraphics.client.impl.util.SVGUtil;
import org.vaadin.flowgraphics.client.shape.Circle;

import static org.junit.jupiter.api.Assertions.*;

class DrawingAreaTest {
    @BeforeEach
    public void fakeVaadin() {
        MockVaadin.setup();
    }
    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void smoke() {
        new DrawingArea(100, 100).flush();
    }

    @Test
    public void simpleScenario() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        Circle circle = new Circle(100, 100, 50);
        circle.setFillColor("red");
        canvas.add(circle);

        UI.getCurrent().add(canvas);
        MockVaadin.clientRoundtrip();

        assertEquals("<svg xmlns=\"http://www.w3.org/2000/svg\" overflow=\"hidden\" width=\"100\" height=\"100\">\n" +
                " <defs></defs><circle fill=\"red\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" r=\"50\" cx=\"100\" cy=\"100\"></circle>\n" +
                "</svg>", canvas.getElement().getProperty("innerHTML"));
    }

    @Test
    public void simpleScenario2() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        UI.getCurrent().add(canvas);
        Circle circle = new Circle(100, 100, 50);
        circle.setFillColor("red");
        canvas.add(circle);

        MockVaadin.clientRoundtrip();

        assertEquals("<svg xmlns=\"http://www.w3.org/2000/svg\" overflow=\"hidden\" width=\"100\" height=\"100\">\n" +
                " <defs></defs><circle fill=\"red\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" r=\"50\" cx=\"100\" cy=\"100\"></circle>\n" +
                "</svg>", canvas.getElement().getProperty("innerHTML"));
    }

    @Test
    public void customizeSvgElement() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        UI.getCurrent().add(canvas);
        canvas.add(new Circle(100, 100, 50));
        Element theSVG = canvas.getSvgElement();
        theSVG.removeAttr("width");
        theSVG.removeAttr("height");
        SVGUtil.setAttributeNS(theSVG, "viewBox", "0 0 20 20");
        SVGUtil.setAttributeNS(theSVG, "overflow", "overlay");

        MockVaadin.clientRoundtrip();

        assertEquals("<svg xmlns=\"http://www.w3.org/2000/svg\" overflow=\"overlay\" viewBox=\"0 0 20 20\">\n" +
                " <defs></defs><circle fill=\"white\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" r=\"50\" cx=\"100\" cy=\"100\"></circle>\n" +
                "</svg>", canvas.getElement().getProperty("innerHTML"));
    }
}
