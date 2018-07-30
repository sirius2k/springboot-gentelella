package kr.co.redbrush.webapp.security;

import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.service.AccountService;
import kr.co.redbrush.webapp.service.LoginHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
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
        accountService.processLoginSuccess(secureAccount.getAccount());

        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}
