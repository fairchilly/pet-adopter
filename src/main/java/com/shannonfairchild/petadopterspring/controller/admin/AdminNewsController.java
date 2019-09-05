package com.shannonfairchild.petadopterspring.controller.admin;

import com.shannonfairchild.petadopterspring.model.News;
import com.shannonfairchild.petadopterspring.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/admin/news")
@Controller
public class AdminNewsController {

    private final NewsService newsService;

    public AdminNewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public String showAllNews(Model model){
        Set<News> news = new HashSet<>();
        newsService.findAll().forEach(news::add);
        model.addAttribute("news", news);

        return "/admin/news/index";
    }
}
