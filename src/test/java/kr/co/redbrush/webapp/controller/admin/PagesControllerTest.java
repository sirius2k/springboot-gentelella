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
public class PagesControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private PagesController pagesController = new PagesController();

    @Before
    public void before() {
    }

    @Test
    public void testPages() throws Exception {
        String pageType = "ecommerce";
        String view = pagesController.pages(model, pageType);

        assertThat("Unexpected value.", view, is("pages_" + pageType));
        assertThat("Unexpected value.", model.get("title"), is("Page " + pageType));
    }

    @Test
    public void testPagesProjects() throws Exception {
        String pageType = "overivew";
        String view = pagesController.projectPages(model, pageType);

        assertThat("Unexpected value.", view, is("pages_projects_" + pageType));
        assertThat("Unexpected value.", model.get("title"), is("Page Projects " + pageType));
    }
}
