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
    @Autowired
    UserRepository userRepository;
    @Override
    public User create(UserInfoRequest request) {


        try{
            User user = userRepo.findById(request.getUser().getId()).orElse(null);

            UserInfo info = new UserInfo();
            info.setFatherName(request.getFatherName());
            info.setMotherName(request.getMotherName());
            info.setAddress(request.getAddress());
            user.setUserInfo(info);

            return userRepository.save(user);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
