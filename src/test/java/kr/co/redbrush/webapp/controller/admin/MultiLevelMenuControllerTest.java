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
public class MultiLevelMenuControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private MultiLevelMenuController multiLevelMenuController = new MultiLevelMenuController();

    @Before
    public void before() {
    }

    @Test
    public void testMultiLevelMenu() throws Exception {
        Integer level = 2;
        String view = multiLevelMenuController.multiLevelMenu(model, level);

        assertThat("Unexpected value.", view, is("multi_level_menu_" + level));
        assertThat("Unexpected value.", model.get("title"), is("Multi Level Menu : level " + level));
    }
}
