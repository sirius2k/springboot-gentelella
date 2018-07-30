package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@Slf4j
public class FormController {

    @GetMapping({"/form/{formType}"})
    public String form(Map<String, Object> model, @PathVariable("formType") String formType) {
        model.put("title", "Form " + formType);

        return "form_" + formType;
    }
}