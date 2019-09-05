package com.shannonfairchild.petadopterspring.controller.admin;

import com.shannonfairchild.petadopterspring.model.Pet;
import com.shannonfairchild.petadopterspring.services.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/admin/pets")
@Controller
public class PetManagementController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "/admin/pets/createOrUpdatePetForm";

    private final PetService petService;

    public PetManagementController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("")
    public String showAllPets(Model model) {
        Set<Pet> pets = new HashSet<>();
        petService.findAll().forEach(pets::add);
        model.addAttribute("pets", pets);

        return "/admin/pets/index";
    }

    @GetMapping("/create")
    public String initCreatePetForm(Model model) {
        model.addAttribute("pet", Pet.builder().build());
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/create")
    public String processCreateNewsForm(@Valid Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/admin/pets";
        }
    }

    @GetMapping("/edit/{petId}")
    public String initUpdateNewsForm(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/edit/{petId}")
    public String processUpdateNewsForm(@Valid Pet pet, BindingResult result, @PathVariable Long petId) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            pet.setId(petId);
            petService.save(pet);

            return "redirect:/admin/pets";
        }
    }

    @DeleteMapping("delete/{petId}")
    public String deleteNews(@PathVariable("petId") Long petId) {
        petService.deleteById(petId);

        return "redirect:/admin/pets";
    }
}
