package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@Slf4j
public class AuthenticationController {

    @GetMapping("/signin")
    public String login(Map<String, Object> model) {
        LOGGER.debug("GET /signin called!");

        return "signin";
    }
}