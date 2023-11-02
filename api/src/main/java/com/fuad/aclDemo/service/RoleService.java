package com.fuad.aclDemo.service;

import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.enums.UserType;

public interface RoleService {
    Role getRoleByUserType(UserType userType);
}
