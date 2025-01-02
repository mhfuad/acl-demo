package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.request.UserRequest;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    Page<UserResponse> getAllUsers(Pageable pageable);

    Optional<User> byId(Long id);

}
