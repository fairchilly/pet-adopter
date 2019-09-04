package com.shannonfairchild.petadopterspring.controller;

import com.shannonfairchild.petadopterspring.model.News;
import com.shannonfairchild.petadopterspring.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping("/news")
@Controller
public class NewsController {
    private static final String VIEWS_NEWS_CREATE_OR_UPDATE_FORM = "/news/createOrUpdatePetForm";

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public String showNews(Model model){
        Set<News> news = newsService.findAll();
        model.addAttribute("news", news);

        return "/news/index";
    }

    @GetMapping("/{newsId}")
    public String viewNews(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsService.findById(newsId);
        model.addAttribute("news", news);

        return "/news/view";
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateNewsForm(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsService.findById(newsId);
        model.addAttribute("news", news);

        return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{newsId}/edit")
    public String processUpdateNewsForm(@Valid News news, BindingResult result, @PathVariable)
}