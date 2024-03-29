package com.shannonfairchild.petadopterspring.repositories;

import com.shannonfairchild.petadopterspring.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Set<Pet> findTop5ByFeaturedTrueOrderByCreatedDate();
}
