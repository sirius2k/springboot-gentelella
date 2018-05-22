package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Controller
@Slf4j
public class AuthenticationController {

    @GetMapping("/login/form")
    public String loginForm(HttpServletRequest request, Map<String, Object> model, String error) {
        setErrorCondition(model, error);

        CsrfToken token = (CsrfToken)request.getSession().getAttribute(CsrfToken.class.getName() + ".CSRF_TOKEN");

        LOGGER.debug("csrfToken : {}", token);

        return "login";
    }

    private void setErrorCondition(Map<String, Object> model, String error) {
        model.put("error", BooleanUtils.toBoolean(error));
    }

}