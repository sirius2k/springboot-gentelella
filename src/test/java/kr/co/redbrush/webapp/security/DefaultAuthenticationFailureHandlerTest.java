package kr.co.redbrush.webapp.security;

import kr.co.redbrush.webapp.domain.Account;
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
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private AccountService accountService;

    @Mock
    private AccessHistoryService accessHistoryService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private int passwordFailureMaxCount = 5;
    private String userId = "userId";
    private Account account = new Account();

    @Before
    public void before() {
        ReflectionTestUtils.setField(defaultAuthenticationFailureHandler, "passwordFailureMaxCount", passwordFailureMaxCount);

        when(request.getParameter("id")).thenReturn(userId);
        when(accountService.getAccount(userId)).thenReturn(account);
    }

    @Test
    public void testOnAuthenticationFailureWithBadCredential() throws Exception {
        int passwordFailureCount = account.getPasswordFailureCount();
        String comment = "Bad Credential";
        AuthenticationException authenticationException = new BadCredentialsException(comment);

        testOnAuthenticationFailure(comment, authenticationException);

        verify(accountService).update(argThat(account -> (account.getPasswordFailureCount() == (passwordFailureCount + 1) && !account.isLocked())));
    }

    @Test
    public void testOnAuthenticationFailureWithBadCredentialAndMaxPassfailureCountExceeded() throws Exception {
        String comment = "Bad Credential";
        AuthenticationException authenticationException = new BadCredentialsException(comment);

        account.setPasswordFailureCount(passwordFailureMaxCount);

        testOnAuthenticationFailure(comment, authenticationException);

        verify(accountService).update(argThat(account -> checkAccountLocked(account, passwordFailureMaxCount)));
    }

    private boolean checkAccountLocked(Account account, int passwordFailureCount) {
        return (account.getPasswordFailureCount() == (passwordFailureCount + 1)) && account.isLocked();
    }

    @Test
    public void testOnAuthenticationFailureWithCredentialExpired() throws Exception {
        String comment = "Credential expired";
        AuthenticationException authenticationException = new CredentialsExpiredException(comment);

        testOnAuthenticationFailure(comment, authenticationException);
    }

    @Test
    public void testOnAuthenticationFailureWithLocked() throws Exception {
        String comment = "Locked";
        AuthenticationException authenticationException = new LockedException(comment);

        testOnAuthenticationFailure(comment, authenticationException);
    }

    @Test
    public void testOnAuthenticationFailureWithAccountExpired() throws Exception {
        String comment = "AccountExpired";
        AuthenticationException authenticationException = new AccountExpiredException(comment);

        testOnAuthenticationFailure(comment, authenticationException);
    }

    private void testOnAuthenticationFailure(String comment, AuthenticationException authenticationException) throws Exception {
        defaultAuthenticationFailureHandler.onAuthenticationFailure(request, response, authenticationException);

        verify(accessHistoryService).insert(argThat(
                accessHistory -> accessHistory.isLoggedIn()==false && accessHistory.getComment().equals(comment)
        ));
    }
}