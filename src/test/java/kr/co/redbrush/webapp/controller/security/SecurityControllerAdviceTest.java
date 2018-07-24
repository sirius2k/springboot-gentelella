package kr.co.redbrush.webapp.controller.security;

import kr.co.redbrush.webapp.controller.ControllerTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.verify;

@Slf4j
public class SecurityControllerAdviceTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private SecurityControllerAdvice securityControllerAdvice = new SecurityControllerAdvice();

    @Mock
    private HttpServletRequest request;

    @Before
    public void before() {
    }

    @Test
    public void testGetCsrf() throws Exception {
        securityControllerAdvice.getCsrf(request);

        verify(request).getAttribute(SecurityControllerAdvice.CSRF_ATTRIBUTE_NAME);
    }
}
