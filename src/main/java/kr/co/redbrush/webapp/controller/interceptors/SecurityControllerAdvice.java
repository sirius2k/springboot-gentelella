package kr.co.redbrush.webapp.controller.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "kr.co.redbrush.webapp.controller")
@Slf4j
public class SecurityControllerAdvice {
    private static final String CSRF_ATTRIBUTE_NAME = "_csrf";

    @ModelAttribute("_csrf")
    public Object getHttpServletRequest(HttpServletRequest request) {
        return request.getAttribute(CSRF_ATTRIBUTE_NAME);
    }
}
