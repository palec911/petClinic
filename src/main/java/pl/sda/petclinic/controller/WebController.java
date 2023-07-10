package pl.sda.petclinic.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.petclinic.services.PetService;

@Controller
public class WebController {

    PetService petService;

    public WebController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/hello")
    public String homepage(Model model) {
        model.addAttribute("theDate", new java.util.Date());

        return "helloworld";

    }

    @GetMapping("/pets")
    public String getPets(Model model) {
        model.addAttribute("pets", petService.getAll());

        return "list-pets";
    }
}
