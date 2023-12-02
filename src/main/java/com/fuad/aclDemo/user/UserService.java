package com.fuad.aclDemo.user;

import com.fuad.aclDemo.user.UserRegistrationRequest;
import com.fuad.aclDemo.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest userRegistrationRequest);

    Page<UserResponse> getAllUsers(Pageable pageable);

    Optional<User> byId(Long id);


}
