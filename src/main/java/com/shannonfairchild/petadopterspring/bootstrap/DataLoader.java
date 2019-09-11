package com.shannonfairchild.petadopterspring.bootstrap;

import com.shannonfairchild.petadopterspring.model.*;
import com.shannonfairchild.petadopterspring.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@Component
@Profile("default")
public class DataLoader implements CommandLineRunner {

    @Autowired
    Environment env;

    private final RoleService roleService;
    private final UserService userService;
    private final PageService pageService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final NewsService newsService;

    public DataLoader(RoleService roleService, UserService userService, PageService pageService, PetTypeService petTypeService, PetService petService, NewsService newsService) {
        this.roleService = roleService;
        this.userService = userService;
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

            log.info("Loading users and roles...");
            loadUsersAndRoles();

            log.info("Loading pages...");
            loadPages();

            log.info("Loading news...");
            loadNews();

            log.info("Loading pets");
            loadPets();
        }
    }

    private void loadUsersAndRoles() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        Role userRole = Role.builder().name("ROLE_USER").build();
        roleService.save(userRole);
        Role adminRole = Role.builder().name("ROLE_ADMIN").build();
        roleService.save(adminRole);

        User user = User.builder()
                .email("user@gmail.com")
                .firstName("Normal")
                .lastName("User")
                .password(secret)
                .enabled(true)
                .roles(new HashSet<>())
                .build();
        user.addRole(userRole);
        userService.save(user);

        User admin = User.builder()
                .email("admin@gmail.com")
                .firstName("Admin")
                .lastName("User")
                .password(secret)
                .enabled(true)
                .roles(new HashSet<>())
                .build();
        admin.addRole(adminRole);
        userService.save(admin);

        User master = User.builder()
                .email("master@gmail.com")
                .firstName("Master")
                .lastName("User")
                .password(secret)
                .enabled(true)
                .roles(new HashSet<>())
                .build();
        master.addRoles(new HashSet<>(Arrays.asList(userRole, adminRole)));
        userService.save(master);

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
                    .visible(true)
                    .build();
            newsService.save(newsItem);
        }
    }

    private void loadPets() {
        PetType cat = PetType.builder().description("Cat").build();
        petTypeService.save(cat);
        PetType dog = PetType.builder().description("Dog").build();
        petTypeService.save(dog);

        Pet john = Pet.builder()
                .name("John")
                .sex(Sex.Male)
                .description("This is a test description!")
                .birthDate(LocalDate.now().minusYears(2))
                .featured(true)
                .type(dog)
                .build();
        petService.save(john);
        Pet paul = Pet.builder()
                .name("Paul")
                .sex(Sex.Male)
                .description("He likes to play catch!")
                .birthDate(LocalDate.now().minusYears(5).minusMonths(2))
                .type(dog)
                .build();
        petService.save(paul);
        Pet ringo = Pet.builder()
                .name("Ringo")
                .sex(Sex.Male)
                .description("Ringo is a quiet cat!")
                .birthDate(LocalDate.now().minusYears(2).minusDays(4))
                .type(cat)
                .build();
        petService.save(ringo);
        Pet george = Pet.builder()
                .name("George")
                .sex(Sex.Male)
                .description("George does well among other pets!")
                .birthDate(LocalDate.now().minusYears(7))
                .type(cat)
                .build();
        petService.save(george);
    }
}
