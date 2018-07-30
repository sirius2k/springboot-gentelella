package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@Slf4j
public class UIElementController {

    @GetMapping({"/uielement/{elementType}"})
    public String uiElement(Map<String, Object> model, @PathVariable("elementType") String elementType) {
        model.put("title", "UI Element " + elementType);

        return "uielement_" + elementType;
    }
}