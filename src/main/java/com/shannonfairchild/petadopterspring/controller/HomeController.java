package com.shannonfairchild.petadopterspring.controller;

import com.shannonfairchild.petadopterspring.model.Pet;
import com.shannonfairchild.petadopterspring.services.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Slf4j
@Controller
public class HomeController {

    private final PetService petService;

    public HomeController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("")
    public String index(Model model){
        Set<Pet> pets = petService.findByFeaturedTrue();
        model.addAttribute("pets", pets);

        return "home/index";
    }
}
