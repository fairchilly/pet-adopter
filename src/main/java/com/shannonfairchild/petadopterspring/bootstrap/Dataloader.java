package com.shannonfairchild.petadopterspring.bootstrap;

import com.shannonfairchild.petadopterspring.model.Pet;
import com.shannonfairchild.petadopterspring.model.PetType;
import com.shannonfairchild.petadopterspring.model.Sex;
import com.shannonfairchild.petadopterspring.services.PetService;
import com.shannonfairchild.petadopterspring.services.PetTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Dataloader implements CommandLineRunner {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public Dataloader(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Pet Types
        PetType cat = PetType.builder().description("Cat").build();
        petTypeService.save(cat);
        PetType dog = PetType.builder().description("Dog").build();
        petTypeService.save(dog);

        // Pets
        Pet john = Pet.builder()
                .name("John")
                .sex(Sex.MALE)
                .birthDate(LocalDate.now().minusYears(2))
                .build();
        petService.save(john);
        Pet paul = Pet.builder()
                .name("Paul")
                .sex(Sex.MALE)
                .birthDate(LocalDate.now().minusYears(5).minusMonths(2))
                .build();
        petService.save(paul);
        Pet ringo = Pet.builder()
                .name("Ringo")
                .sex(Sex.MALE)
                .birthDate(LocalDate.now().minusYears(2).minusDays(4))
                .build();
        petService.save(ringo);
        Pet george = Pet.builder()
                .name("George")
                .sex(Sex.MALE)
                .birthDate(LocalDate.now().minusYears(7))
                .build();
        petService.save(george);
    }
}
