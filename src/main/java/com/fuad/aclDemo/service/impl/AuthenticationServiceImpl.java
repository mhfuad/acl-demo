package com.fuad.aclDemo.service.impl;

import com.fuad.aclDemo.dto.request.AuthenticationRequest;
import com.fuad.aclDemo.dto.request.RefreshTokenRequest;
import com.fuad.aclDemo.dto.response.AuthenticationResponse;
import com.fuad.aclDemo.repository.TokenRepository;
import com.fuad.aclDemo.service.AuthenticationService;
import com.fuad.aclDemo.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        return null;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        return null;
    }

    @Override
    public void logout(String username) {

    }
}
