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
public class IndexControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private IndexController indexController = new IndexController();

    @Before
    public void before() {
    }

    @Test
    public void testIndex() throws Exception {
        String view = indexController.index(model);

        assertThat("Unexpected value.", view, is("index1"));
        assertThat("Unexpected value.", model.get("title"), is("Dashboard 1"));
    }

    @Test
    public void testIndex2() throws Exception {
        int indexNum = 2;
        String view = indexController.index2(model, indexNum);

        assertThat("Unexpected value.", view, is("index" + indexNum));
        assertThat("Unexpected value.", model.get("title"), is("Dashboard " + indexNum));
    }
}
