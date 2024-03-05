package com.fuad.aclDemo.role;

import com.fuad.aclDemo.user.UserTypeEnum;

import java.util.List;

public interface RoleService {
    Role getRoleByUserType(UserTypeEnum userType);

    List<Role> all();

    Role save(RoleRequest role);

    void delete(Long id);
}
