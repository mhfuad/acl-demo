package com.fuad.aclDemo.controllers;

import com.fuad.aclDemo.common.ResponseObject;
import com.fuad.aclDemo.dto.request.UserRegistrationRequest;
import com.fuad.aclDemo.dto.response.UserResponse;
import com.fuad.aclDemo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    //public ResponseObject<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult result){
    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult result){
        log.info("Received user registration request: {}", request);

        if (result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok(userService.registerUser(request));


//        return ResponseObject.<UserResponse>builder()
//                .status(ResponseObject.ResponseStatus.SUCCESSFUL)
//                .message("User Register successful")
//                .data(userService.registerUser(request))
//                .build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseObject<Page<UserResponse>> getAllUsers(@PageableDefault(sort = "id",
                                                            direction = Sort.Direction.DESC
                                                            )Pageable pageable
                                                        ){
        return ResponseObject.<Page<UserResponse>>builder()
                .status(ResponseObject.ResponseStatus.SUCCESSFUL)
                .data(userService.getAllUsers(pageable))
                .build();
    }
}
