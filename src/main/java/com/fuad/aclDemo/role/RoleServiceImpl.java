package com.fuad.aclDemo.role;

import com.fuad.aclDemo.user.UserTypeEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role getRoleByUserType(UserTypeEnum userType) {
        RoleNameEnum roleName = switch (userType) {
            case CUSTOMER -> RoleNameEnum.ROLE_CUSTOMER;
            case ADMIN -> RoleNameEnum.ROLE_ADMIN;
            case TEACHER -> RoleNameEnum.ROLE_TEACHER;
            case STUDENT -> RoleNameEnum.ROLE_STUDENT;
        };

        return repository.findByName(roleName.toString()).orElseThrow(() -> new
                EntityNotFoundException("Role not found"));
    }

    @Override
    public List<Role> all() {
        return repository.findAll();
    }
}
