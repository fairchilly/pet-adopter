package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.News;

import java.util.List;

public interface NewsService extends CrudService<News, Long> {
    List<News> findTop3ByOrderByCreatedDateDesc();
}
