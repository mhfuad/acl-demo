package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.request.AuthenticationRequest;
import com.fuad.aclDemo.dto.request.RefreshTokenRequest;
import com.fuad.aclDemo.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request);

    void logout(String username);
}
