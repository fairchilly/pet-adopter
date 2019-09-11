package com.shannonfairchild.petadopterspring.bootstrap;


import com.shannonfairchild.petadopterspring.services.PrivilegeService;
import com.shannonfairchild.petadopterspring.services.RoleService;
import com.shannonfairchild.petadopterspring.services.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserService userService;
    private final RoleService roleService;
    private final PrivilegeService privilegeService;

    public SecurityDataLoader(UserService userService, RoleService roleService, PrivilegeService privilegeService) {
        this.userService = userService;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
