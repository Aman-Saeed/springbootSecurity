package com.springboot.springbootSecurity.dto;

import com.springboot.springbootSecurity.entities.enums.Permission;
import com.springboot.springbootSecurity.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {

   private String email;
   private String password;
   private String name;
   private Set<Role> roles;
   private Set<Permission> permissions;

}
