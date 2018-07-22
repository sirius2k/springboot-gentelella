package kr.co.redbrush.webapp.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
@Slf4j
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(Map<String, Object> model) {
        model.put("title", "Dashboard 1");

        return "index1";
    }

    @GetMapping("/index/{indexNum}")
    public String index2(Map<String, Object> model, @PathVariable("indexNum") int indexNum) {
        model.put("title", "Dashboard " + indexNum);

        return "index" + indexNum;
    }
}