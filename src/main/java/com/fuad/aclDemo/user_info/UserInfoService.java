package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.user.User;
import com.fuad.aclDemo.user.UserRegistrationRequest;
import com.fuad.aclDemo.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserInfoService {

    User create(UserInfoRequest request);

}
