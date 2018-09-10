package kr.co.redbrush.webapp.security;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.service.AccessHistoryService;
import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
public class DefaultAuthenticationFailureHandlerTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    public DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler = new DefaultAuthenticationFailureHandler();

    @Mock
    private AccessHistoryService accessHistoryService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private String userId = "userId";

    @Before
    public void before() {
    }

    @Test
    public void testOnAuthenticationFailure() throws Exception {
        AuthenticationException authenticationException = new BadCredentialsException("Bad Credential");

        defaultAuthenticationFailureHandler.onAuthenticationFailure(request, response, authenticationException);

        verify(accessHistoryService).insert(argThat(accessHistory -> accessHistory.isLoggedIn()==false));
    }
}