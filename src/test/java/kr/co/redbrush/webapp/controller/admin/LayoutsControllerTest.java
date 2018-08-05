package kr.co.redbrush.webapp.controller.admin;

import kr.co.redbrush.webapp.controller.ControllerTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class LayoutsControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private LayoutsController layoutsController = new LayoutsController();

    @Before
    public void before() {
    }

    @Test
    public void testLayouts() throws Exception {
        String chartType = "sidebar";
        String view = layoutsController.uiElement(model, chartType);

        assertThat("Unexpected value.", view, is("layouts_fixed_" + chartType));
        assertThat("Unexpected value.", model.get("title"), is("Layout " + chartType));
    }
}
