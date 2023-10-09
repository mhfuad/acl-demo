package com.fuad.aclDemo.service.impl;

import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.enums.RoleName;
import com.fuad.aclDemo.enums.UserType;
import com.fuad.aclDemo.repository.RoleRepository;
import com.fuad.aclDemo.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByUserType(UserType userType) {
        RoleName roleName = switch (userType) {
            case CUSTOMER -> RoleName.ROLE_CUSTOMER;
            case ADMIN -> RoleName.ROLE_ADMIN;
        };
        System.out.println("role name " + roleRepository.findByName(roleName.toString()));

        return roleRepository.findByName(roleName.toString()).orElseThrow(() -> new
                EntityNotFoundException("Role not found"));
    }
}
