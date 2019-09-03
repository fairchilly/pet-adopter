package com.shannonfairchild.petadopterspring.repositories;

import com.shannonfairchild.petadopterspring.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}
