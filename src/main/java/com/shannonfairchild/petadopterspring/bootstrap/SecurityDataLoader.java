package com.shannonfairchild.petadopterspring.bootstrap;


import com.shannonfairchild.petadopterspring.services.RoleService;
import com.shannonfairchild.petadopterspring.services.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SecurityDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserService userService;
    private final RoleService roleService;

    public SecurityDataLoader(UserService userService,
                              RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
    }
}
