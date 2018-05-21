package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AuthenticationController {

    @GetMapping("/login/form")
    public String loginForm(HttpServletRequest request, Map<String, Object> model, String error) {
        setErrorCondition(model, error);

        return "login";
    }

    private void setErrorCondition(Map<String, Object> model, String error) {
        model.put("error", BooleanUtils.toBoolean(error));
    }

}