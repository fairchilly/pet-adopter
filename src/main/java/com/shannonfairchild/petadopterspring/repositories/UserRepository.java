package com.shannonfairchild.petadopterspring.repositories;

import com.shannonfairchild.petadopterspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
