package com.fuad.aclDemo.service;

import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.dto.response.RoleRequest;
import com.fuad.aclDemo.enums.UserTypeEnum;

import java.util.List;

public interface RoleService {
    Role getRoleByUserType(UserTypeEnum userType);

    List<Role> all();

    Role save(RoleRequest role);

    void delete(Long id);

    boolean roleAssignToUser(Long userId, Long roleId);
}
