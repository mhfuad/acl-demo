package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.request.UserRequest;
import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.enums.UserTypeEnum;
import com.fuad.aclDemo.mapper.Mapper;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.dto.response.UserResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse registerUser(UserRequest request) {
        if (userRepository.existsByUsername(request.getEmail()))
            throw new EntityExistsException(String.format("Email %s already exists", request.getEmail()));

        Role role = roleService.getRoleByUserType(UserTypeEnum.STUDENT);

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .roles(Collections.singleton(role))
                .build();
        return Mapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(Mapper::toUserResponse);
    }

    @Override
    public Optional<User> byId(Long id) {
        return userRepository.findById(id);
    }

}
