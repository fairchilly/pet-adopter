package com.shannonfairchild.petadopterspring.controller;

import com.shannonfairchild.petadopterspring.model.Pet;
import com.shannonfairchild.petadopterspring.services.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pets")
    public String showPets(Model model){
        Set<Pet> pets = petService.findAll();
        model.addAttribute("pets", pets);

        return "pets/index";
    }
}
