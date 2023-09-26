package com.fuad.aclDemo.service.impl;

import com.fuad.aclDemo.dto.request.UserRegistrationRequest;
import com.fuad.aclDemo.dto.response.UserResponse;
import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.mapper.Mapper;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.service.RoleService;
import com.fuad.aclDemo.service.UserService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.existByUsername(userRegistrationRequest.getEmail()))
            throw new EntityExistsException(String.format("Email %s already exists", userRegistrationRequest.getEmail()));

        Role role = roleService.getRoleByUserType(userRegistrationRequest.getType());

        User user = User.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .username(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .phoneNumber(userRegistrationRequest.getPhoneNumber())
                .roles(Collections.singleton(role))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        return Mapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(Mapper::toUserResponse);
    }
}
