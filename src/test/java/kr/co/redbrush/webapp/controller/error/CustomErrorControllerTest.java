package kr.co.redbrush.webapp.controller.error;

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
import static org.mockito.Mockito.when;

@Slf4j
public class CustomErrorControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private CustomErrorController customErrorController = new CustomErrorController();

    @Before
    public void before() {
    }

    @Test
    public void testError() throws Exception {
        String statusCode = "404";
        String exceptionType = "exceptionType";
        String message = "message";
        String requestUri = "requestUri";
        String exception = "exception";
        String servletName = "servletName";

        when(request.getAttribute("javax.servlet.error.status_code")).thenReturn(statusCode);
        when(request.getAttribute("javax.servlet.error.exception_type")).thenReturn(exceptionType);
        when(request.getAttribute("javax.servlet.error.message")).thenReturn(message);
        when(request.getAttribute("javax.servlet.error.request_uri")).thenReturn(requestUri);
        when(request.getAttribute("javax.servlet.error.exception")).thenReturn(exception);
        when(request.getAttribute("javax.servlet.error.servlet_name")).thenReturn(servletName);

        String view = customErrorController.handleError(modelMap, request);

        assertThat("Unexpected value.", view, is("error"));
        assertThat("Unexpected value.", modelMap.get("statusCode"), is(statusCode));
        assertThat("Unexpected value.", modelMap.get("exceptionType"), is(exceptionType));
        assertThat("Unexpected value.", modelMap.get("message"), is(message));
        assertThat("Unexpected value.", modelMap.get("requestUri"), is(requestUri));
        assertThat("Unexpected value.", modelMap.get("exception"), is(exception));
        assertThat("Unexpected value.", modelMap.get("servletName"), is(servletName));
    }
}
