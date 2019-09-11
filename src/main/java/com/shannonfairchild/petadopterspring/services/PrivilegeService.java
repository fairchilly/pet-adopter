package com.shannonfairchild.petadopterspring.services;

import com.shannonfairchild.petadopterspring.model.Privilege;

public interface PrivilegeService {
    Privilege findByName(String name);
    Privilege save(Privilege privilege);
}
