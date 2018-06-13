package kr.co.redbrush.webapp.controller.admin;

import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AuthenticationController {
    public static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
    public static final String VIEW_LOGIN = "login";
    public static final String VIEW_LOGIN_REDIRECT = "/login/form";
    public static final String VIEW_SIGNUP = "signupForm";
    public static final String VIEW_SIGNUP_REDIRECT = "/signupForm/form";

    @Autowired
    private AccountService accountService;

    @GetMapping("/login/form")
    public ModelAndView loginForm(HttpServletRequest request, Map<String, Object> model, String error) {
        setErrorCondition(request, model, error);

        if (accountService.getCount() == 0) {
            return new ModelAndView(new RedirectView(VIEW_SIGNUP_REDIRECT));
        } else {
            return new ModelAndView(VIEW_LOGIN, model);
        }
    }

    private void setErrorCondition(HttpServletRequest request, Map<String, Object> model, String error) {
        AuthenticationException authenticationException = (AuthenticationException)request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION);

        model.put("error", BooleanUtils.toBoolean(error));

        if (authenticationException!=null) {
            model.put("errorMessage", authenticationException.getMessage());
        }
    }

    @GetMapping("/signup/form")
    public ModelAndView signupForm(HttpServletRequest request, Map<String, Object> model) {
        if (accountService.getCount() == 0) {
            return new ModelAndView(VIEW_SIGNUP);
        } else {
            return new ModelAndView(new RedirectView(VIEW_LOGIN_REDIRECT));
        }
    }
}