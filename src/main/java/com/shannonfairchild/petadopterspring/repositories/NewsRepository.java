package com.shannonfairchild.petadopterspring.repositories;

import com.shannonfairchild.petadopterspring.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
