package kr.co.redbrush.webapp.controller.admin;

import kr.co.redbrush.webapp.controller.ControllerTestBase;
import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.AuthenticationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@Slf4j
public class AuthenticationControllerTest extends ControllerTestBase {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private AuthenticationController authenticationController = new AuthenticationController();

    @Mock
    private AuthenticationException authenticationException;

    @Mock
    private AccountService accountService;

    @Before
    public void before() {
    }

    @Test
    public void testLoginFormWithoutError() throws Exception {
        String error = null;
        boolean expectedErrorParam = false;

        when(session.getAttribute(AuthenticationController.SPRING_SECURITY_LAST_EXCEPTION)).thenReturn(null);
        when(accountService.getCount()).thenReturn(1L);

        String view = authenticationController.loginForm(request, model, error);

        assertThat("Unexpected value.", view, is(AuthenticationController.VIEW_LOGIN));
        assertThat("Unexpected value.", model.get("error"), is(expectedErrorParam));
        assertThat("Unexpected value.", model.get("errorMessage"), nullValue());
    }

    @Test
    public void testLoginFormWithError() throws Exception {
        String error = "true";
        String errorMessage = "Error";
        boolean expectedErrorParam = true;

        when(session.getAttribute(AuthenticationController.SPRING_SECURITY_LAST_EXCEPTION)).thenReturn(authenticationException);
        when(authenticationException.getMessage()).thenReturn(errorMessage);
        when(accountService.getCount()).thenReturn(1L);

        String view = authenticationController.loginForm(request, model, error);

        assertThat("Unexpected value.", view, is(AuthenticationController.VIEW_LOGIN));
        assertThat("Unexpected value.", model.get("error"), is(expectedErrorParam));
        assertThat("Unexpected value.", model.get("errorMessage"), is(errorMessage));
    }

    @Test
    public void testLoginFormWithNoReigsteredAccount() throws Exception {
        String error = null;
        boolean expectedErrorParam = false;

        when(session.getAttribute(AuthenticationController.SPRING_SECURITY_LAST_EXCEPTION)).thenReturn(null);
        when(accountService.getCount()).thenReturn(0L);

        String view = authenticationController.loginForm(request, model, error);

        assertThat("Unexpected value.", view, is(AuthenticationController.VIEW_SIGNUP));
        assertThat("Unexpected value.", model.get("error"), is(expectedErrorParam));
        assertThat("Unexpected value.", model.get("errorMessage"), nullValue());
    }
}
