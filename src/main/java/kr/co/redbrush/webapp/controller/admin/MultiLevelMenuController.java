package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@Slf4j
public class MultiLevelMenuController {

    @GetMapping({"/menu/level/{level}"})
    public String multiLevelMenu(Map<String, Object> model, @PathVariable("level") Integer level) {
        model.put("title", "Multi Level Menu : level " + level);

        return "multi_level_menu_" + level;
    }
}