package org.vaadin.flowgraphics.client.shape.path;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathStepTest {
    @Test
    public void closePath() {
        assertEquals("z", new ClosePath().toString());
    }

    @Test
    public void moveTo() {
        assertEquals("M1 2", new MoveTo(false, 1, 2).toString());
        assertEquals("m1 2", new MoveTo(true, 1, 2).toString());
    }

    @Test
    public void lineTo() {
        assertEquals("L1 2", new LineTo(false, 1, 2).toString());
        assertEquals("l1 2", new LineTo(true, 1, 2).toString());
    }

    @Test
    public void curveTo() {
        final CurveTo curve = new CurveTo(false, 1, 2, 3, 4, 5, 6);
        assertEquals(1, curve.getX1());
        assertEquals(2, curve.getY1());
        assertEquals(3, curve.getX2());
        assertEquals(4, curve.getY2());
        assertEquals(5, curve.getX());
        assertEquals(6, curve.getY());
        assertEquals("C1 2 3 4 5 6", curve.toString());
        assertEquals("c1 2 3 4 5 6", new CurveTo(true, 1, 2, 3, 4, 5, 6).toString());
    }

    @Test
    public void arc() {
        final Arc arc = new Arc(false, 10, 20, 30, true, false, 40, 50);
        assertEquals(10, arc.getRx());
        assertEquals(20, arc.getRy());
        assertEquals(30, arc.getxAxisRotation());
        assertTrue(arc.isLargeArc());
        assertFalse(arc.isSweep());
        assertEquals("A10,20 30 1,0 40,50", arc.toString());
        assertEquals("a1,2 3 0,1 4,5", new Arc(true, 1, 2, 3, false, true, 4, 5).toString());
    }
}
