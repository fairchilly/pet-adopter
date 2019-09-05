package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.Page;

import java.util.List;

public interface PageService extends CrudService<Page, Long> {
    Page findByPath(String path);
    List<Page> findAllByOrderByRank();
}
