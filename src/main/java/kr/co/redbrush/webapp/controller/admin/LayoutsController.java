package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@Slf4j
public class LayoutsController {

    @GetMapping({"/layouts/fixed/{layoutType}"})
    public String uiElement(Map<String, Object> model, @PathVariable("layoutType") String layoutType) {
        model.put("title", "Layout " + layoutType);

        return "layouts_fixed_" + layoutType;
    }
}