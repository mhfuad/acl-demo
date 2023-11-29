package com.fuad.aclDemo.role;

import com.fuad.aclDemo.enums.UserType;

public interface RoleService {
    Role getRoleByUserType(UserType userType);
}
