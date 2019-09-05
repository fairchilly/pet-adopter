package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.Pet;

import java.util.Set;

public interface PetService extends CrudService<Pet, Long> {
    Set<Pet> findByFeaturedTrue();
}
