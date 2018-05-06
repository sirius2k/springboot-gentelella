package kr.co.redbrush.webapp.admin.controller;

import com.samskivert.mustache.Mustache;
import kr.co.redbrush.webapp.admin.view.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice
public class LayoutAdvice {
    @Autowired
    private Mustache.Compiler compiler;

    // TOOD : Implement layout
    /*
    @ModelAttribute("layout")
    public Mustache.Lambda layout(Map<String, Object> model) {
        return new Layout(compiler);
    }
    */

    @ModelAttribute("layout")
    public Mustache.Lambda layout() {
        return new Layout();
    }
}
