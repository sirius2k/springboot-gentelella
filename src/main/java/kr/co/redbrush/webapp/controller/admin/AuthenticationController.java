package kr.co.redbrush.webapp.controller.admin;

import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AuthenticationController {
    public static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
    public static final String VIEW_LOGIN = "login";
    public static final String VIEW_SIGNUP = "signup";

    @Autowired
    private AccountService accountService;

    @GetMapping("/login/form")
    public String loginForm(HttpServletRequest request, Map<String, Object> model, String error) {
        setErrorCondition(request, model, error);

        if (accountService.getCount() == 0) {
            return VIEW_SIGNUP;
        } else {
            return VIEW_LOGIN;
        }
    }

    private void setErrorCondition(HttpServletRequest request, Map<String, Object> model, String error) {
        AuthenticationException authenticationException = (AuthenticationException)request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION);

        model.put("error", BooleanUtils.toBoolean(error));

        if (authenticationException!=null) {
            model.put("errorMessage", authenticationException.getMessage());
        }
    }

}