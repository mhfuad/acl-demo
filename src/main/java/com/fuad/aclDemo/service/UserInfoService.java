package com.fuad.aclDemo.service;

import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.dto.request.UserInfoRequest;

public interface UserInfoService {

    User create(UserInfoRequest request);

}
