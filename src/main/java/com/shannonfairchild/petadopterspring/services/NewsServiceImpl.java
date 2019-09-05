package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.News;
import com.shannonfairchild.petadopterspring.repositories.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Set<News> findAll() {
        Set<News> news = new HashSet<>();
        newsRepository.findAll().forEach(news::add);

        return news;
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public void delete(News news) {
        newsRepository.delete(news);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
