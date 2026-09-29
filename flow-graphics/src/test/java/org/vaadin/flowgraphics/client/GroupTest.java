package org.vaadin.flowgraphics.client;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vaadin.flowgraphics.client.shape.Circle;
import org.vaadin.flowgraphics.client.shape.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    @BeforeEach
    public void fakeVaadin() {
        MockVaadin.setup();
    }
    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void rendering() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        UI.getCurrent().add(canvas);
        final Group group = new Group();
        group.add(new Circle(10, 10, 5));
        group.add(new Rectangle(1, 2, 3, 4));
        canvas.add(group);

        MockVaadin.clientRoundtrip();

        assertEquals("<svg xmlns=\"http://www.w3.org/2000/svg\" overflow=\"hidden\" width=\"100\" height=\"100\">\n" +
                " <defs></defs><g>\n" +
                "  <circle fill=\"white\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" r=\"5\" cx=\"10\" cy=\"10\"></circle>\n" +
                "  <rect fill=\"white\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" x=\"1\" y=\"2\" width=\"3\" height=\"4\"></rect>\n" +
                " </g>\n" +
                "</svg>", canvas.getElement().getProperty("innerHTML"));
    }

    @Test
    public void addAndRemove() {
        final Group group = new Group();
        final Circle circle = new Circle(0, 0, 1);
        assertSame(circle, group.add(circle));
        assertSame(group, circle.getParent());
        assertEquals(1, group.getVectorObjectCount());
        assertSame(circle, group.getVectorObject(0));

        assertSame(circle, group.remove(circle));
        assertNull(circle.getParent());
        assertEquals(0, group.getVectorObjectCount());
        assertEquals("<g></g>", group.getElement().toString());
    }

    @Test
    public void removeForeignChildReturnsNull() {
        final Group group = new Group();
        final Group other = new Group();
        final Circle circle = new Circle(0, 0, 1);
        other.add(circle);
        assertNull(group.remove(circle));
        assertNull(group.bringToFront(circle));
        assertSame(other, circle.getParent());
    }

    @Test
    public void addingChildOfAnotherGroupFails() {
        final Group other = new Group();
        final Circle circle = new Circle(0, 0, 1);
        other.add(circle);
        assertThrows(IllegalStateException.class, () -> new Group().add(circle));
    }

    @Test
    public void insert() {
        final Group group = new Group();
        final Circle a = new Circle(0, 0, 1);
        final Circle b = new Circle(0, 0, 2);
        final Circle c = new Circle(0, 0, 3);
        group.add(a);
        group.add(b);
        group.insert(c, 1);
        assertEquals(3, group.getVectorObjectCount());
        assertSame(a, group.getVectorObject(0));
        assertSame(c, group.getVectorObject(1));
        assertSame(b, group.getVectorObject(2));
        assertEquals("1", group.getElement().child(0).attr("r"));
        assertEquals("3", group.getElement().child(1).attr("r"));
        assertEquals("2", group.getElement().child(2).attr("r"));
    }

    @Test
    public void insertOutOfRangeFails() {
        final Group group = new Group();
        assertThrows(IndexOutOfBoundsException.class, () -> group.insert(new Circle(0, 0, 1), -1));
        assertThrows(IndexOutOfBoundsException.class, () -> group.insert(new Circle(0, 0, 1), 1));
    }

    @Test
    public void bringToFront() {
        final Group group = new Group();
        final Circle a = new Circle(0, 0, 1);
        final Circle b = new Circle(0, 0, 2);
        group.add(a);
        group.add(b);
        assertSame(a, group.bringToFront(a));
        assertEquals("2", group.getElement().child(0).attr("r"));
        assertEquals("1", group.getElement().child(1).attr("r"));
    }

    @Test
    public void clear() {
        final Group group = new Group();
        final Circle a = new Circle(0, 0, 1);
        final Rectangle b = new Rectangle(0, 0, 1, 1);
        group.add(a);
        group.add(b);
        group.clear();
        assertEquals(0, group.getVectorObjectCount());
        assertEquals(0, group.getElement().childrenSize());
        assertNull(a.getParent());
        assertNull(b.getParent());
    }

    @Test
    public void childOfAttachedGroupIsAttached() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        UI.getCurrent().add(canvas);
        final Group group = new Group();
        canvas.add(group);
        assertTrue(group.isAttached());

        final Circle circle = new Circle(0, 0, 1);
        group.add(circle);
        assertTrue(circle.isAttached());

        group.remove(circle);
        assertFalse(circle.isAttached());
    }

    @Test
    public void attachingGroupAttachesItsChildren() {
        final DrawingArea canvas = new DrawingArea(100, 100);
        final Group group = new Group();
        final Circle circle = new Circle(0, 0, 1);
        group.add(circle);
        canvas.add(group);
        assertFalse(circle.isAttached());

        UI.getCurrent().add(canvas);
        assertTrue(group.isAttached());
        assertTrue(circle.isAttached());

        UI.getCurrent().remove(canvas);
        assertFalse(group.isAttached());
        assertFalse(circle.isAttached());
    }
}
