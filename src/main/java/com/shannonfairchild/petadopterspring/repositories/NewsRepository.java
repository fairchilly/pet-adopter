package com.shannonfairchild.petadopterspring.repositories;

import com.shannonfairchild.petadopterspring.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findTop3ByOrderByCreatedDateDesc();
    List<News> findAllByOrderByCreatedDateDesc();
}
