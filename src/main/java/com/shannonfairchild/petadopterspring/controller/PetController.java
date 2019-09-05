package com.shannonfairchild.petadopterspring.controller;

import com.shannonfairchild.petadopterspring.model.Page;
import com.shannonfairchild.petadopterspring.model.Pet;
import com.shannonfairchild.petadopterspring.services.PageService;
import com.shannonfairchild.petadopterspring.services.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequestMapping("/pets")
@Controller
public class PetController {
    private static final String VIEWS_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PageService pageService;
    private final PetService petService;

    public PetController(PageService pageService, PetService petService) {
        this.pageService = pageService;
        this.petService = petService;
    }

    @GetMapping("")
    public String showPets(Model model) {
        Set<Pet> pets = petService.findAll();
        model.addAttribute("pets", pets);

//        List<Page> pages = pageService.findAllByOrderByRank();
//        model.addAttribute("pages", pages);

        return "pets/index";
    }

    @GetMapping("/{petId}")
    public String viewPet(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        return "pets/view";
    }

    @GetMapping("/create")
    public String initCreatePetForm(Model model) {
        model.addAttribute("pet", Pet.builder().build());

        return VIEWS_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/create")
    public String processCreatePetForm(@Valid Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PET_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/pets/" + pet.getId();
        }
    }

    @GetMapping("/{petId}/edit")
    public String initUpdatePetForm(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute(pet);

        return "pets/view";
    }

    @PostMapping("/{petId}/edit")
    public String processUpdatePetForm(@Valid Pet pet,
                                       BindingResult result,
                                       @PathVariable("petId") Long petId) {
        if (result.hasErrors()) {
            return VIEWS_PET_CREATE_OR_UPDATE_FORM;
        } else {
            pet.setId(petId);
            Pet savedPet = petService.save(pet);
            return "redirect:/pets/" + savedPet.getId();
        }
    }

    @DeleteMapping("/{petId}")
    public String deletePet(@PathVariable("petId") Long petId) {
        petService.deleteById(petId);

        return "redirect:/pets";
    }
}
