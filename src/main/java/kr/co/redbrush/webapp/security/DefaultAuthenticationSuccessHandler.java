package kr.co.redbrush.webapp.security;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.LoginHistory;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.service.AccountService;
import kr.co.redbrush.webapp.service.LoginHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException, ServletException {

        SecureAccount secureAccount = (SecureAccount)authentication.getPrincipal();

        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setLoginDate(new Date());
        loginHistoryService.insert(loginHistory);

        Account account = secureAccount.getAccount();
        account.setLastLogin(loginHistory.getLoginDate());
        accountService.update(account);
    }
}
