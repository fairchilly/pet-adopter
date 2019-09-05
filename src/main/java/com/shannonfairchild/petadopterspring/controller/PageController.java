package com.shannonfairchild.petadopterspring.controller;

import com.shannonfairchild.petadopterspring.model.Page;
import com.shannonfairchild.petadopterspring.services.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pages")
@Controller
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/{pagePath}")
    public String viewPage(@PathVariable("pagePath") String pagePath, Model model) {
        Page page = pageService.findByPath(pagePath);
        model.addAttribute("page", page);

        return "pages/view";
    }
}
