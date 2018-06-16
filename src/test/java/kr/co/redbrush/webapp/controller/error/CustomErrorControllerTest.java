package kr.co.redbrush.webapp.controller.error;

import kr.co.redbrush.webapp.controller.ControllerTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;

import javax.servlet.RequestDispatcher;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
    public void testHandleError() throws Exception {
        testHandleError(null);
        testHandleError(HttpStatus.FORBIDDEN);
        testHandleError(HttpStatus.NOT_FOUND);
        testHandleError(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void testHandleError(HttpStatus httpStatus) {
        String expectedView = "error";

        if (httpStatus != null) {
            expectedView = "error_" + httpStatus.value();
            when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(httpStatus.value());
        }

        String view = customErrorController.handleError(request, modelMap);

        assertThat("Unexpected value.", view, is(expectedView));

        if (httpStatus==null) {
            assertThat("Unexpected value.", modelMap.get("reason"), nullValue());
        } else {
            assertThat("Unexpected value.", modelMap.get("reason"), is(httpStatus.getReasonPhrase()));
        }
    }
}
