package com.shannonfairchild.petadopterspring.advice;

import com.shannonfairchild.petadopterspring.model.Page;
import com.shannonfairchild.petadopterspring.services.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class PageAdvice {

    private final PageService pageService;

    public PageAdvice(PageService pageService) {
        this.pageService = pageService;
    }

    @ModelAttribute("navPages")
    public List<Page> getNavPages(){
        List<Page> pages = new ArrayList<>();
        pageService.findAllByOrderByPriority().forEach(pages::add);

        return pages;
    }
}
