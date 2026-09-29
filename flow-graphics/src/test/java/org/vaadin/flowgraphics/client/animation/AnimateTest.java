package org.vaadin.flowgraphics.client.animation;

import org.junit.jupiter.api.Test;
import org.vaadin.flowgraphics.client.shape.Circle;

import static org.junit.jupiter.api.Assertions.*;

class AnimateTest {
    @Test
    public void getters() {
        final Circle circle = new Circle(0, 0, 10);
        final Animate animate = new Animate(circle, "radius", 10, 50, 1000);
        assertSame(circle, animate.getTarget());
        assertEquals("radius", animate.getProperty());
        assertEquals(10, animate.getStartValue());
        assertEquals(50, animate.getEndValue());
        assertEquals(1000, animate.getDuration());
    }

    @Test
    public void startIsNoOpOnServer() {
        final Circle circle = new Circle(0, 0, 10);
        final boolean[] completed = {false};
        final Animate animate = new Animate(circle, "radius", 10, 50, 1000) {
            @Override
            protected void onComplete() {
                completed[0] = true;
            }
        };
        animate.start();
        animate.stop();
        assertEquals(10, circle.getRadius());
        assertFalse(completed[0]);
    }
}
