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

    private static String radii(DrawingArea canvas) {
        final StringBuilder sb = new StringBuilder();
        for (Element child : canvas.getSvgElement().children()) {
            if (!child.tagName().equals("defs")) {
                sb.append(child.attr("r"));
            }
        }
        return sb.toString();
    }

    @Test
    public void addAndRemove() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        UI.getCurrent().add(canvas);
        final Circle circle = new Circle(0, 0, 1);
        assertSame(circle, canvas.add(circle));
        assertSame(canvas, circle.getParent());
        assertEquals(1, canvas.getVectorObjectCount());
        assertSame(circle, canvas.getVectorObject(0));

        MockVaadin.clientRoundtrip();
        assertTrue(canvas.getElement().getProperty("innerHTML").contains("<circle"));

        assertSame(circle, canvas.remove(circle));
        assertNull(circle.getParent());
        assertEquals(0, canvas.getVectorObjectCount());
        MockVaadin.clientRoundtrip();
        assertFalse(canvas.getElement().getProperty("innerHTML").contains("<circle"));
    }

    @Test
    public void removeForeignChildReturnsNull() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        final Group other = new Group();
        final Circle circle = new Circle(0, 0, 1);
        other.add(circle);
        assertNull(canvas.remove(circle));
        assertNull(canvas.bringToFront(circle));
        assertSame(other, circle.getParent());
    }

    @Test
    public void insertGoesAfterDefs() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        final Circle a = new Circle(0, 0, 1);
        final Circle b = new Circle(0, 0, 2);
        final Circle c = new Circle(0, 0, 3);
        canvas.add(a);
        canvas.add(b);
        canvas.insert(c, 0);
        assertSame(c, canvas.getVectorObject(0));
        assertSame(a, canvas.getVectorObject(1));
        assertSame(b, canvas.getVectorObject(2));
        assertEquals("defs", canvas.getSvgElement().child(0).tagName());
        assertEquals("312", radii(canvas));
    }

    @Test
    public void insertOutOfRangeFails() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        assertThrows(IndexOutOfBoundsException.class, () -> canvas.insert(new Circle(0, 0, 1), -1));
        assertThrows(IndexOutOfBoundsException.class, () -> canvas.insert(new Circle(0, 0, 1), 1));
    }

    @Test
    public void bringToFront() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        final Circle a = new Circle(0, 0, 1);
        final Circle b = new Circle(0, 0, 2);
        canvas.add(a);
        canvas.add(b);
        assertSame(a, canvas.bringToFront(a));
        assertEquals("21", radii(canvas));
    }

    @Test
    public void clear() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        final Circle a = new Circle(0, 0, 1);
        final Circle b = new Circle(0, 0, 2);
        canvas.add(a);
        canvas.add(b);
        canvas.clear();
        assertEquals(0, canvas.getVectorObjectCount());
        assertNull(a.getParent());
        assertNull(b.getParent());
        assertEquals("", radii(canvas));
        assertEquals("defs", canvas.getSvgElement().child(0).tagName());
    }

    @Test
    public void size() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        canvas.setWidth(200);
        canvas.setHeight("300px");
        assertEquals("200px", canvas.getWidth());
        assertEquals("300px", canvas.getHeight());
        assertEquals("200", canvas.getSvgElement().attr("width"));
        assertEquals("300", canvas.getSvgElement().attr("height"));
    }

    @Test
    public void nonPixelSizeFails() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        assertThrows(IllegalArgumentException.class, () -> canvas.setWidth("50%"));
        assertThrows(IllegalArgumentException.class, () -> canvas.setHeight("5em"));
        assertThrows(IllegalArgumentException.class, () -> canvas.setWidth("abcpx"));
    }

    @Test
    public void styleName() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        canvas.addClassName("stale");
        canvas.setStyleName("chart");
        assertEquals("chart chart-svg", canvas.getClassName());
    }

    @Test
    public void rendererString() {
        assertEquals("SVG", new DrawingArea(100, 100).getRendererString());
    }

    @Test
    public void attachFollowsTheUI() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        final Circle circle = new Circle(0, 0, 1);
        canvas.add(circle);
        assertFalse(circle.isAttached());

        UI.getCurrent().add(canvas);
        assertTrue(circle.isAttached());

        UI.getCurrent().remove(canvas);
        assertFalse(circle.isAttached());
    }

    @Test
    public void flushLazyCoalescesMutations() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        UI.getCurrent().add(canvas);
        canvas.add(new Circle(0, 0, 1));
        canvas.add(new Circle(0, 0, 2));
        assertEquals("", canvas.getElement().getProperty("innerHTML", ""));

        MockVaadin.clientRoundtrip();
        final String html = canvas.getElement().getProperty("innerHTML");
        assertTrue(html.contains("r=\"1\"") && html.contains("r=\"2\""), html);
    }
}
