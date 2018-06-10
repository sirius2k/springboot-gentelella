package kr.co.redbrush.webapp.controller.error;

import kr.co.redbrush.webapp.SpringBootWebApplication;
import kr.co.redbrush.webapp.controller.admin.IndexController;
import kr.co.redbrush.webapp.test.TestVariables;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.servlet.RequestDispatcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomErrorController.class)
@ContextConfiguration(classes ={ SpringBootWebApplication.class})
@TestPropertySource(locations = TestVariables.APPLICATION_TEST_PROPERTIES)
@Slf4j
public class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private MockHttpServletRequest request;

    @Before
    public void before() {
        request = new MockHttpServletRequest();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testHttp404Error() throws Exception {
        testErrorPage(HttpStatus.FORBIDDEN);
        testErrorPage(HttpStatus.NOT_FOUND);
        testErrorPage(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void testErrorPage(HttpStatus httpStatus) throws Exception {
        String expectedView = "page_" + httpStatus.value();

        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, httpStatus);

        RequestBuilder requestBuilder = get("/error")
                .accept(MediaType.ALL)
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, httpStatus.value());

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("reason", httpStatus.getReasonPhrase()))
                .andExpect(view().name(expectedView));
    }

    @Test
    public void testGetErrorPath() throws Exception {
        CustomErrorController errorController = new CustomErrorController();

        assertThat("Unexpected value", errorController.getErrorPath(), is("/error"));
    }

}
