package com.fuad.aclDemo.service;

import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.repository.UserInfoRepository;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.dto.request.UserInfoRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return null;
//        try{
//            User user = userRepo.findById(request.getUser().getId()).orElse(null);
//
//            UserInfo info = new UserInfo();
//            info.setFatherName(request.getFatherName());
//            info.setMotherName(request.getMotherName());
//            info.setAddress(request.getAddress());
//            user.setUserInfo(info);
//
//            return userRepository.save(user);
//        }catch (Exception e){
//            System.out.println(e);
//            return null;
//        }

    }
}
