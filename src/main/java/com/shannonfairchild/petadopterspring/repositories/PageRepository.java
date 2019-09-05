package com.shannonfairchild.petadopterspring.repositories;

import com.shannonfairchild.petadopterspring.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {
    Page findByPath(String path);
    List<Page> findAllByOrderByRank();
}
