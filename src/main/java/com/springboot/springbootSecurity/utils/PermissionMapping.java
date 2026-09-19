package com.springboot.springbootSecurity.utils;

import com.springboot.springbootSecurity.entities.enums.Permission;
import com.springboot.springbootSecurity.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springboot.springbootSecurity.entities.enums.Permission.*;
import static com.springboot.springbootSecurity.entities.enums.Role.*;

public class PermissionMapping {

    private static final Map<Role, Set<Permission>> rolePermissionMap = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATOR, Set.of(POST_CREATE, POST_EDIT, USER_EDIT),
            ADMIN, Set.of(POST_CREATE, USER_EDIT, POST_EDIT, USER_DELETE,USER_CREATE, POST_DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthorities(Role role) {

        return rolePermissionMap.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }

}
