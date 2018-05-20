package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@Slf4j
public class AuthenticationController {

    @GetMapping("/login/form")
    public String loginForm(Map<String, Object> model, String error) {
        LOGGER.debug("GET /login/form called!");

        model.put("error", BooleanUtils.toBoolean(error));

        return "login";
    }

}