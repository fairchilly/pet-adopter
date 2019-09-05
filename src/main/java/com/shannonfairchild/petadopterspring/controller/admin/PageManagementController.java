package com.shannonfairchild.petadopterspring.controller.admin;

import com.shannonfairchild.petadopterspring.model.Page;
import com.shannonfairchild.petadopterspring.services.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/admin/pages")
@Controller
public class PageManagementController {
    private static final String VIEWS_PAGES_CREATE_OR_UPDATE_FORM = "/admin/pages/createOrUpdatePageForm";

    private final PageService pageService;

    public PageManagementController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("")
    public String showAllPages(Model model) {
        Set<Page> pages = new HashSet<>();
        pageService.findAll().forEach(pages::add);
        model.addAttribute("pages", pages);

        return "/admin/pages/index";
    }

    @GetMapping("/create")
    public String initCreatePageForm(Model model) {
        model.addAttribute("page", Page.builder().build());
        return VIEWS_PAGES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/create")
    public String processCreatePageForm(@Valid Page page, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_PAGES_CREATE_OR_UPDATE_FORM;
        } else {
            pageService.save(page);
            return "redirect:/admin/pages";
        }
    }

    @GetMapping("/edit/{pageId}")
    public String initUpdatePageForm(@PathVariable("pageId") Long pageId, Model model) {
        Page page = pageService.findById(pageId);
        model.addAttribute("page", page);

        return VIEWS_PAGES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/edit/{pageId}")
    public String processUpdateNewsForm(@Valid Page page, BindingResult result, @PathVariable Long pageId) {
        if (result.hasErrors()) {
            return VIEWS_PAGES_CREATE_OR_UPDATE_FORM;
        } else {
            page.setId(pageId);
            pageService.save(page);

            return "redirect:/admin/pages";
        }
    }

    @DeleteMapping("delete/{pageId}")
    public String deleteNews(@PathVariable("pageId") Long pageId) {
        pageService.deleteById(pageId);

        return "redirect:/admin/pages";
    }
}
