package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
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

        // TODO : WIP get csrf token
        // sessionAttribute name : SPRING_SECURITY_SAVED_REQUEST

        Enumeration<String> e = request.getSession().getAttributeNames();
        while (e.hasMoreElements()) {
            String attributeName = e.nextElement();
            LOGGER.debug("sessionAttribute name : {}, value : {}", attributeName, request.getSession().getAttribute(attributeName));
        }

        CsrfToken token = (CsrfToken)request.getSession().getAttribute("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN");
        request.setAttribute("csrfToken", token);
        request.setAttribute("errorMessage", request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION"));

        if (token!=null) {
            LOGGER.debug("csrfToken : {}, parameterName : {}, token : {}", token, token.getParameterName(), token.getToken());
        }

        return "login";
    }

    private void setErrorCondition(Map<String, Object> model, String error) {
        model.put("error", BooleanUtils.toBoolean(error));
    }

}