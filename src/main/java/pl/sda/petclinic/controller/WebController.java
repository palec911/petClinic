package pl.sda.petclinic.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/hello")
    public String homepage(Model model) {
        model.addAttribute("theDate", new java.util.Date());

        return "helloworld";

    }
}
