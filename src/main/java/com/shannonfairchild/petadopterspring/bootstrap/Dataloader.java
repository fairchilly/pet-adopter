package com.shannonfairchild.petadopterspring.bootstrap;

import com.shannonfairchild.petadopterspring.model.*;
import com.shannonfairchild.petadopterspring.services.NewsService;
import com.shannonfairchild.petadopterspring.services.PageService;
import com.shannonfairchild.petadopterspring.services.PetService;
import com.shannonfairchild.petadopterspring.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
@Component
@Profile("default")
public class Dataloader implements CommandLineRunner {

    @Autowired
    Environment env;

    private final PageService pageService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final NewsService newsService;

    public Dataloader(PageService pageService, PetTypeService petTypeService, PetService petService, NewsService newsService) {
        this.pageService = pageService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.newsService = newsService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Run if profile is default or if there is no data in database
        if (Arrays.stream(env.getActiveProfiles())
                .anyMatch(env -> env.equalsIgnoreCase("default"))
        || pageService.findAll().size() == 0) {
            log.info("Loading pages...");
            loadPages();

            log.info("Loading news...");
            loadNews();

            log.info("Loading pet types...");
            loadPetTypes();

            log.info("Loading pets");
            loadPets();
        }
    }

    private void loadPages() {
        Page aboutPage = Page.builder()
                .title("About")
                .path("about")
                .html_content("<h1>This is my about page!</h1>")
                .priority(1)
                .build();
        pageService.save(aboutPage);
        Page contactUsPage = Page.builder()
                .title("Contact Us")
                .path("contact")
                .html_content("<h1>This is my contact us page!</h1>")
                .priority(2)
                .build();
        pageService.save(contactUsPage);
    }

    private void loadNews() {
        for (int x = 1; x <= 50; x++) {
            News newsItem = News.builder()
                    .author("Shannon Fairchild")
                    .title("Article " + x)
                    .subtitle("This is a subtitle!")
                    .html_content("<h1>Test content!</h1>")
                    .createdAt(LocalDate.now())
                    .build();
            newsService.save(newsItem);
        }
    }

    private void loadPetTypes() {
        PetType cat = PetType.builder().description("Cat").build();
        petTypeService.save(cat);
        PetType dog = PetType.builder().description("Dog").build();
        petTypeService.save(dog);
    }

    private void loadPets() {
        Pet john = Pet.builder()
                .name("John")
                .sex(Sex.Male)
                .description("This is a test description!")
                .birthDate(LocalDate.now().minusYears(2))
                .featured(true)
                .build();
        petService.save(john);
        Pet paul = Pet.builder()
                .name("Paul")
                .sex(Sex.Male)
                .description("He likes to play catch!")
                .birthDate(LocalDate.now().minusYears(5).minusMonths(2))
                .build();
        petService.save(paul);
        Pet ringo = Pet.builder()
                .name("Ringo")
                .sex(Sex.Male)
                .description("Ringo is a quiet cat!")
                .birthDate(LocalDate.now().minusYears(2).minusDays(4))
                .build();
        petService.save(ringo);
        Pet george = Pet.builder()
                .name("George")
                .sex(Sex.Male)
                .description("George does well among other pets!")
                .birthDate(LocalDate.now().minusYears(7))
                .build();
        petService.save(george);
    }
}
