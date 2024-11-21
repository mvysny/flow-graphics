package demoapp;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vaadin.flowgraphics.client.DrawingArea;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;

public class MainRouteTest {
    private static final Routes routes = new Routes().autoDiscoverViews("demoapp");
    @BeforeEach
    public void fakeVaadin() {
        MockVaadin.setup(routes);
    }
    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void smoke() {
        UI.getCurrent().navigate(MainRoute.class);
        _assertOne(MainRoute.class);
        _assertOne(DrawingArea.class);
    }
}
