package com.shannonfairchild.petadopterspring.controller.admin;

import com.shannonfairchild.petadopterspring.model.News;
import com.shannonfairchild.petadopterspring.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/admin/news")
@Controller
public class NewsManagementController {
    private static final String VIEWS_NEWS_CREATE_OR_UPDATE_FORM = "admin/news/createOrUpdatePetForm";

    private final NewsService newsService;

    public NewsManagementController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public String showAllNews(Model model) {
        Set<News> news = new HashSet<>();
        newsService.findAll().forEach(news::add);
        model.addAttribute("news", news);

        return "/admin/news/index";
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
            return "redirect:/admin/news/" + news.getId();
        }
    }

    @GetMapping("/edit/{newsId}")
    public String initUpdateNewsForm(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsService.findById(newsId);
        model.addAttribute("news", news);

        return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/edit/{newsId}")
    public String processUpdateNewsForm(@Valid News news, BindingResult result, @PathVariable Long newsId) {
        if (result.hasErrors()) {
            return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
        } else {
            news.setId(newsId);
            News savedNews = newsService.save(news);

            return "redirect:/admin/news/" + savedNews.getId();
        }
    }

    @DeleteMapping("delete/{newsId}")
    public String deleteNews(@PathVariable("newsId") Long newsId) {
        newsService.deleteById(newsId);

        return "redirect:/admin/news";
    }
}
