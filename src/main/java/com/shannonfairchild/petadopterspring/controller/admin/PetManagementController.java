package com.shannonfairchild.petadopterspring.controller.admin;

import com.shannonfairchild.petadopterspring.model.Pet;
import com.shannonfairchild.petadopterspring.model.PetType;
import com.shannonfairchild.petadopterspring.model.Sex;
import com.shannonfairchild.petadopterspring.services.PetService;
import com.shannonfairchild.petadopterspring.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/admin/pets")
@Controller
public class PetManagementController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "/admin/pets/createOrUpdatePetForm";

    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetManagementController(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
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
        model.addAttribute("pet", new Pet());

        List<Sex> sexes = Arrays.asList(Sex.values());
        model.addAttribute("sexes", sexes);

        List<PetType> petTypes = new ArrayList<>();
        petTypeService.findAll().forEach(petTypes::add);
        model.addAttribute("petTypes", petTypes);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/create")
    public String processCreatePetForm(@Valid Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/admin/pets";
        }
    }

    @GetMapping("/edit/{petId}")
    public String initUpdatePetForm(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        List<Sex> sexes = Arrays.asList(Sex.values());
        model.addAttribute("sexes", sexes);

        List<PetType> petTypes = new ArrayList<>();
        petTypeService.findAll().forEach(petTypes::add);
        model.addAttribute("petTypes", petTypes);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/edit/{petId}")
    public String processUpdatePetForm(@Valid Pet pet, BindingResult result, @PathVariable Long petId) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            pet.setId(petId);
            petService.save(pet);

            return "redirect:/admin/pets";
        }
    }

    @DeleteMapping("delete/{petId}")
    public String deletePet(@PathVariable("petId") Long petId) {
        petService.deleteById(petId);

        return "redirect:/admin/pets";
    }
}
