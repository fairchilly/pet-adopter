package com.shannonfairchild.petadopterspring.controller.admin;

import com.shannonfairchild.petadopterspring.model.News;
import com.shannonfairchild.petadopterspring.services.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/admin/news")
@Controller
public class NewsManagementController {
    private static final String VIEWS_NEWS_CREATE_OR_UPDATE_FORM = "admin/news/createOrUpdateNewsForm";

    private final NewsService newsService;

    public NewsManagementController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public String showAllNews(Model model) {
        List<News> news = new ArrayList<>();
        newsService.findAll().forEach(news::add);
        model.addAttribute("news", news);

        return "/admin/news/index";
    }

    @GetMapping("/create")
    public String initCreateNewsForm(Model model) {
        model.addAttribute("news", new News());
        return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/create")
    public String processCreateNewsForm(@Valid News news,
                                        BindingResult result,
                                        Model model,
                                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("news", news);
            return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
        } else {
            newsService.save(news);
            redirectAttributes
                    .addFlashAttribute("success", true);

            return "redirect:/admin/news";
        }
    }

    @GetMapping("/edit/{newsId}")
    public String initUpdateNewsForm(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsService.findById(newsId);
        model.addAttribute("news", news);

        return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/edit/{newsId}")
    public String processUpdateNewsForm(@Valid News news,
                                        BindingResult result,
                                        Model model,
                                        RedirectAttributes redirectAttributes,
                                        @PathVariable Long newsId) {

        if (result.hasErrors()) {
            model.addAttribute("news", news);

            return VIEWS_NEWS_CREATE_OR_UPDATE_FORM;
        } else {
            news.setId(newsId);
            News savedNews = newsService.save(news);
            redirectAttributes
                    .addFlashAttribute("success", true);

            return "redirect:/admin/news";
        }
    }

    @DeleteMapping("delete/{newsId}")
    public String deleteNews(@PathVariable("newsId") Long newsId) {
        newsService.deleteById(newsId);

        return "redirect:/admin/news";
    }
}
