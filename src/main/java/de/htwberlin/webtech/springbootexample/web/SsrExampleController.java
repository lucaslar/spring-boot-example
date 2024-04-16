package de.htwberlin.webtech.springbootexample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SsrExampleController {

    @GetMapping(path = "/")
    public ModelAndView showSsrPage(Model model) {
        model.addAttribute("someData", "Hello from Controller!");
        return new ModelAndView("example");
    }
}
