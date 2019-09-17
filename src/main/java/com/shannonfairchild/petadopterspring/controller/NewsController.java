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
import java.util.List;

@RequestMapping("/news")
@Controller
public class NewsController {
    private static final String VIEWS_NEWS_CREATE_OR_UPDATE_FORM = "/news/createOrUpdatePetForm";

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public String showNews(Model model) {
        List<News> news = newsService.findAllByOrderByCreatedDateDesc();
        model.addAttribute("news", news);

        return "/news/index";
    }

    @GetMapping("/create")
    public String initCreateNewsForm(Model model) {
        model.addAttribute("news", News.builder().build());

        return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/create")
    public String processCreateNewsForm(@Valid News news, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
        } else {
            newsService.save(news);
            return "redirect:/news/" + news.getId();
        }
    }

    @GetMapping("/{newsId}")
    public String viewNews(@PathVariable("newsId") Long newsId, Model model) {
        News newsItem = newsService.findById(newsId);
        model.addAttribute("newsItem", newsItem);

        return "/news/view";
    }

    @GetMapping("/{newsId}/edit")
    public String initUpdateNewsForm(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsService.findById(newsId);
        model.addAttribute("news", news);

        return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{newsId}/edit")
    public String processUpdateNewsForm(@Valid News news, BindingResult result, @PathVariable Long newsId) {
        if (result.hasErrors()) {
            return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
        } else {
            news.setId(newsId);
            News savedNews = newsService.save(news);

            return "redirect:/news/" + savedNews.getId();
        }
    }
}
