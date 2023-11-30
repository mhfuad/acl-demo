package com.fuad.aclDemo.role;

import com.fuad.aclDemo.user.UserTypeEnum;

public interface RoleService {
    Role getRoleByUserType(UserTypeEnum userType);
}
