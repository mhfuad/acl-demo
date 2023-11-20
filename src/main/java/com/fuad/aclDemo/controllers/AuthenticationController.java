package com.fuad.aclDemo.controllers;

import com.fuad.aclDemo.common.ResponseObject;
import com.fuad.aclDemo.dto.request.AuthenticationRequest;
import com.fuad.aclDemo.dto.request.RefreshTokenRequest;
import com.fuad.aclDemo.dto.response.AuthenticationResponse;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody  AuthenticationRequest request, BindingResult result){

        if (result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<?> self(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok(authentication.getName());
        }else {
            return ResponseEntity.ok( "not ok");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@Valid @RequestBody RefreshTokenRequest request, BindingResult result){
        if (result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.refreshToken(request));
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseObject logout(Principal principal){

        authenticationService.logout(principal.getName());

        return ResponseObject.builder()
                .status(ResponseObject.ResponseStatus.SUCCESSFUL)
                .message("User logged out successfully")
                .build();
    }

}
