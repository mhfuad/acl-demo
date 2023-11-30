package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.common.ResponseObject;
import com.fuad.aclDemo.user.UserRegistrationRequest;
import com.fuad.aclDemo.user.UserRepository;
import com.fuad.aclDemo.user.UserResponse;
import com.fuad.aclDemo.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/userInfo")
public class UserInfoController {
    private UserInfoService service;
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository repository;

    //public ResponseObject<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult result){
    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserInfoRequest request){
        return ResponseEntity.ok("create");

        //return ResponseEntity.ok(userService.registerUser(request));
//        return ResponseObject.<UserResponse>builder()
//                .status(ResponseObject.ResponseStatus.SUCCESSFUL)
//                .message("User Register successful")
//                .data(userService.registerUser(request))
//                .build();
    }

}
