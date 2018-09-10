package kr.co.redbrush.webapp.security;

import kr.co.redbrush.webapp.domain.AccessHistory;
import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.service.AccessHistoryService;
import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Slf4j
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccessHistoryService accessHistoryService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        String userId = request.getParameter("id");
        Account account = accountService.getAccount(userId);

        if (authenticationException instanceof BadCredentialsException) {
            processBadCredential(account);
        } else if (authenticationException instanceof CredentialsExpiredException) {
            // TODO : Implement CredentialsExpiredException
        } else if (authenticationException instanceof LockedException) {
            // TODO : Implement LockedException
        } else if (authenticationException instanceof AccountExpiredException) {
            // TODO : Implement AccountExpiredException
        }

        super.onAuthenticationFailure(request, response, authenticationException);
    }

    private void processBadCredential(Account account) {
        AccessHistory accessHistory = new AccessHistory();
        accessHistory.setAccount(account);
        accessHistory.setLoggedIn(false);
        accessHistory.setLoginDate(LocalDateTime.now());
        accessHistoryService.insert(accessHistory);
    }
}
