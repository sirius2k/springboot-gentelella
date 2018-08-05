package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@Slf4j
public class PagesController {

    @GetMapping({"/pages/{pageType}"})
    public String pages(Map<String, Object> model, @PathVariable("pageType") String pageType) {
        model.put("title", "Page " + pageType);

        return "pages_" + pageType;
    }

    @GetMapping({"/pages/projects/{pageType}"})
    public String projectPages(Map<String, Object> model, @PathVariable("pageType") String pageType) {
        model.put("title", "Page Projects " + pageType);

        return "pages_projects_" + pageType;
    }

}