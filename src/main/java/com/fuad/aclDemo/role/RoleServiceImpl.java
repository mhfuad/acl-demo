package com.fuad.aclDemo.role;

import com.fuad.aclDemo.user.UserTypeEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByUserType(UserTypeEnum userType) {
        RoleNameEnum roleName = switch (userType) {
            case CUSTOMER -> RoleNameEnum.ROLE_CUSTOMER;
            case ADMIN -> RoleNameEnum.ROLE_ADMIN;
            case TEACHER -> RoleNameEnum.ROLE_TEACHER;
            case STUDENT -> RoleNameEnum.ROLE_STUDENT;
        };

        return roleRepository.findByName(roleName.toString()).orElseThrow(() -> new
                EntityNotFoundException("Role not found"));
    }
}
