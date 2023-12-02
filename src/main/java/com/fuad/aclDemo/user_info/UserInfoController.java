package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.user.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user_info")
public class UserInfoController {
    private UserInfoService service;
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository repository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

    //public ResponseObject<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult result){
    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserInfoRequest request) throws IOException {
        if(request.getImage().equals("")|| request.getImage().isEmpty() || request.getImage() == null){
            User user = userRepository.findById(request.getUser_id()).orElse(null);
            if(user == null){
                return ResponseEntity.badRequest().body("No user found");
            }
            UserInfo info = new UserInfo();
            info.setFatherName(request.getFatherName());
            info.setMotherName(request.getMotherName());
            info.setAddress(request.getAddress());
            user.setUserInfo(info);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }

        byte[] imageBytes = Base64.decodeBase64(request.getImage());
        try (FileOutputStream fos = new FileOutputStream(UPLOAD_DIRECTORY)){
            fos.write(imageBytes);
        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok("uploads/image.jpg");

    }

}
