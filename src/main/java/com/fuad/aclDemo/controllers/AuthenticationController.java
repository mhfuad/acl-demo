package com.fuad.aclDemo.controllers;

import com.fuad.aclDemo.common.ResponseObject;
import com.fuad.aclDemo.dto.request.AuthenticationRequest;
import com.fuad.aclDemo.dto.request.RefreshTokenRequest;
import com.fuad.aclDemo.dto.response.AuthenticationResponse;
import com.fuad.aclDemo.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request)
    {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody @Valid RefreshTokenRequest request){
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
