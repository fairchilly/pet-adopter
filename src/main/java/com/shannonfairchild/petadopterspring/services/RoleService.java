package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.Role;

public interface RoleService {
    Role findByName(String name);
    Role save(Role role);
    void deleteAll();
}
