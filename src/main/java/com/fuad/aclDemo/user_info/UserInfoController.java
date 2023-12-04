package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.user.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user_info")
public class UserInfoController {
    private UserInfoService service;
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository repository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

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

        try {
            byte[] decodedBytes = Base64.decodeBase64(request.getImage());
            String fileName = generateUniqueFileName("profile.jpeg");

            Path directoryPath = Paths.get(UPLOAD_DIRECTORY);
            if (!directoryPath.toFile().exists()) {
                directoryPath.toFile().mkdirs();
            }

            Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())) {
                fileOutputStream.write(decodedBytes);
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return ResponseEntity.ok("Base64 image decoded and saved successfully. Filename: " + fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Error decoding and saving the base64 image."+ HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }

}
