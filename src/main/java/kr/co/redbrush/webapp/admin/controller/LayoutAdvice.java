package kr.co.redbrush.webapp.admin.controller;

import com.samskivert.mustache.Mustache;
import kr.co.redbrush.webapp.admin.view.Layout;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LayoutAdvice {
    @ModelAttribute("header")
    public Mustache.Lambda defaults(@ModelAttribute Layout layout) {
        return (frag, out) -> {
            layout.setHeader(frag.execute());
            layout.setHeader("Test");
        };
    }
}
