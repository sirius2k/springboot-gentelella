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
public class FormControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private FormController formController = new FormController();

    @Before
    public void before() {
    }

    @Test
    public void testForm() throws Exception {
        String formType = "general";
        String view = formController.form(model, formType);

        assertThat("Unexpected value.", view, is("form_" + formType));
        assertThat("Unexpected value.", model.get("title"), is("Form " + formType));
    }
}
