package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@Slf4j
public class TablesController {

    @GetMapping({"/tables/{tableType}"})
    public String uiElement(Map<String, Object> model, @PathVariable("tableType") String tableType) {
        model.put("title", "Tables " + tableType);

        return "tables_" + tableType;
    }
}