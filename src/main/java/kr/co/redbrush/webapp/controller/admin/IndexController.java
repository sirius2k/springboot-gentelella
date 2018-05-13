package kr.co.redbrush.webapp.controller.admin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    @GetMapping({"/", "/index", "/index/0"})
    public String index(Map<String, Object> model) {
        LOGGER.debug("Index called model : {}", model);

        model.put("title", "Springboot Web Service");

        return "index";
    }
}