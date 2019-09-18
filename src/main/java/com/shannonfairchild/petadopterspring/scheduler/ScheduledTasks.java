package com.shannonfairchild.petadopterspring.scheduler;

import com.shannonfairchild.petadopterspring.bootstrap.DataLoader;
import com.shannonfairchild.petadopterspring.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    Environment env;

    private final NewsService newsService;
    private final PageService pageService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final RoleService roleService;
    private final UserService userService;
    private final DataLoader dataLoader;

    public ScheduledTasks(NewsService newsService,
                          PageService pageService,
                          PetService petService,
                          PetTypeService petTypeService,
                          RoleService roleService,
                          UserService userService,
                          DataLoader dataLoader) {
        this.newsService = newsService;
        this.pageService = pageService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.roleService = roleService;
        this.userService = userService;
        this.dataLoader = dataLoader;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void ClearAndSeedTables() {

        if (Boolean.parseBoolean(env.getProperty("demo.mode"))) {
            log.info("Clearing...");
            newsService.deleteAll();
            pageService.deleteAll();
            petService.deleteAll();
            petTypeService.deleteAll();
            userService.deleteAll();
            roleService.deleteAll();

            log.info("Seeding...");
            try {
                dataLoader.run();
            } catch (Exception e) {
            }
        }
    }
}
