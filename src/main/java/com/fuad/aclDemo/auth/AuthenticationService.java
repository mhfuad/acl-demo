package com.fuad.aclDemo.auth;

import com.fuad.aclDemo.token.RefreshTokenRequest;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request);

    void logout(String username);
}
