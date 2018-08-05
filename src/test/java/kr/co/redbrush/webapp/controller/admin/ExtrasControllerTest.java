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
public class ExtrasControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private ExtrasController extrasController = new ExtrasController();

    @Before
    public void before() {
    }

    @Test
    public void testExtraPages() throws Exception {
        String pageType = "404";
        String view = extrasController.extraPages(model, pageType);

        assertThat("Unexpected value.", view, is("extras_page_" + pageType));
        assertThat("Unexpected value.", model.get("title"), is("Extra Page " + pageType));
    }
}
