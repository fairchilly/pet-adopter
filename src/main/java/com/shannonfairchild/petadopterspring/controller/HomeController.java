package com.shannonfairchild.petadopterspring.controller;

import com.shannonfairchild.petadopterspring.model.News;
import com.shannonfairchild.petadopterspring.model.Pet;
import com.shannonfairchild.petadopterspring.services.NewsService;
import com.shannonfairchild.petadopterspring.services.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class HomeController {

    private final PetService petService;
    private final NewsService newsService;

    public HomeController(PetService petService, NewsService newsService) {
        this.petService = petService;
        this.newsService = newsService;
    }

    @GetMapping("")
    public String index(Model model){
        Set<Pet> featuredPets = petService.findFeatured();
        model.addAttribute("featuredPets", featuredPets);

        List<News> news = newsService.findTop3ByOrderByCreatedDateDesc();
        model.addAttribute("news", news);

        return "home/index";
    }
}
