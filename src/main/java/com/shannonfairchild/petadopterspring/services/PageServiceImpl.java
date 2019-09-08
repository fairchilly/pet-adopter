package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.Page;
import com.shannonfairchild.petadopterspring.repositories.PageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public Set<Page> findAll() {
        Set<Page> pages = new HashSet<>();
        pageRepository.findAll().forEach(pages::add);

        return pages;
    }

    @Override
    public Page findById(Long id) {
        return pageRepository.findById(id).orElse(null);
    }

    @Override
    public Page save(Page page) {
        return pageRepository.save(page);
    }

    @Override
    public void delete(Page page) {
        pageRepository.delete(page);
    }

    @Override
    public void deleteById(Long id) {
        pageRepository.deleteById(id);
    }

    @Override
    public Page findByPath(String path) {
        return pageRepository.findByPath(path);
    }

    @Override
    public List<Page> findAllByOrderByPriority() {
        List<Page> pages = new ArrayList<>();
        pageRepository.findAllByOrderByPriority().forEach(pages::add);

        return pages;
    }
}
