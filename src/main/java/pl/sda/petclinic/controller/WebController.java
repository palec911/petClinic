package pl.sda.petclinic.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.petclinic.model.Pet;
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

    @PostMapping("/pet")
    public String savePet(@ModelAttribute("pet") Pet pet) {
        petService.createPet(pet);

        return "redirect:/pets";
    }

    @GetMapping("/addPetForm")
    public String addPetForm(Model model) {
        Pet pet = new Pet();
        model.addAttribute(pet);

        return "pet-form";
    }

    @GetMapping("/updatePetForm")
    public String updatePetForm(Model model, @RequestParam("petId") Long petId) {
        Pet petToUpdate = petService.getPetById(petId).get();
        model.addAttribute("pet", petToUpdate);
        return "pet-form";
    }

    @GetMapping("/deletePet")
    public String removePet(@RequestParam("petId") Long petId) {
        petService.deletePetById(petId);
        return "redirect:/pets";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "custom-login";
    }
}
