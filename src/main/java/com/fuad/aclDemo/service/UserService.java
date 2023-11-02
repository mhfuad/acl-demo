package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.request.UserRegistrationRequest;
import com.fuad.aclDemo.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest userRegistrationRequest);

    Page<UserResponse> getAllUsers(Pageable pageable);
}
