package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.mapper.Mapper;
import com.fuad.aclDemo.role.Role;
import com.fuad.aclDemo.role.RoleService;
import com.fuad.aclDemo.user.*;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    public UserInfoRepository repository;
    @Autowired
    UserRepository userRepo;
    @Override
    public UserInfo create(UserInfoRequest request) {
        User user = userRepo.findById(request.getUser().getId()).orElse(null);

        UserInfo info = new UserInfo();
        info.setUser(user);
        info.setFatherName(request.getFatherName());
        info.setMotherName(request.getMotherName());
        info.setAddress(request.getAddress());
        return repository.save(info);
    }
}
