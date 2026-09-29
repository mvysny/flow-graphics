package org.vaadin.flowgraphics.client.shape;

import org.junit.jupiter.api.Test;
import org.vaadin.flowgraphics.client.shape.path.ClosePath;
import org.vaadin.flowgraphics.client.shape.path.LineTo;
import org.vaadin.flowgraphics.client.shape.path.MoveTo;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {
    @Test
    public void rendering() {
        final Path path = new Path(50, 50);
        assertEquals("<path fill=\"white\" fill-opacity=\"1.0\" stroke=\"black\" stroke-opacity=\"1.0\" stroke-width=\"1\" d=\" M50 50\"></path>", path.getElement().toString());
    }

    @Test
    public void rectangleFromJavadoc() {
        final Path path = new Path(50, 50);
        path.lineRelativelyTo(100, 0);
        path.lineRelativelyTo(0, 100);
        path.lineRelativelyTo(-100, 0);
        path.close();
        assertEquals(" M50 50 l100 0 l0 100 l-100 0 z", path.getElement().attr("d"));
        assertEquals(5, path.getStepCount());

        // the javadoc then turns the rectangle into a triangle
        path.setStep(2, new LineTo(true, -50, 100));
        path.removeStep(3);
        assertEquals(" M50 50 l100 0 l-50 100 z", path.getElement().attr("d"));
        assertEquals(4, path.getStepCount());
    }

    @Test
    public void allStepKinds() {
        final Path path = new Path(0, 0);
        path.moveTo(1, 2);
        path.moveRelativelyTo(3, 4);
        path.lineTo(5, 6);
        path.lineRelativelyTo(7, 8);
        path.curveTo(1, 2, 3, 4, 5, 6);
        path.curveRelativelyTo(-1, -2, -3, -4, -5, -6);
        path.arc(10, 20, 30, true, false, 40, 50);
        path.arcRelatively(1, 2, 3, false, true, 4, 5);
        path.close();
        assertEquals(" M0 0 M1 2 m3 4 L5 6 l7 8 C1 2 3 4 5 6 c-1 -2 -3 -4 -5 -6 A10,20 30 1,0 40,50 a1,2 3 0,1 4,5 z",
                path.getElement().attr("d"));
        assertEquals(10, path.getStepCount());
    }

    @Test
    public void positionIsTheFirstMoveTo() {
        final Path path = new Path(50, 60);
        path.lineTo(100, 100);
        assertEquals(50, path.getX());
        assertEquals(60, path.getY());

        path.setX(10);
        path.setY(20);
        assertEquals(10, path.getX());
        assertEquals(20, path.getY());
        assertEquals(" M10 20 L100 100", path.getElement().attr("d"));
    }

    @Test
    public void getStep() {
        final Path path = new Path(50, 60);
        path.close();
        final MoveTo first = (MoveTo) path.getStep(0);
        assertFalse(first.isRelativeCoords());
        assertEquals(50, first.getX());
        assertEquals(60, first.getY());
        assertInstanceOf(ClosePath.class, path.getStep(1));
    }

    @Test
    public void firstStepMustBeAbsoluteMoveTo() {
        final Path path = new Path(0, 0);
        path.lineTo(10, 10);
        assertThrows(IllegalArgumentException.class, () -> path.setStep(0, new MoveTo(true, 1, 1)));
        assertThrows(IllegalArgumentException.class, () -> path.setStep(0, new LineTo(false, 1, 1)));
        assertThrows(IllegalArgumentException.class, () -> path.setStep(0, new ClosePath()));
        assertEquals(" M0 0 L10 10", path.getElement().attr("d"));

        path.setStep(0, new MoveTo(false, 5, 5));
        assertEquals(" M5 5 L10 10", path.getElement().attr("d"));
    }
}
