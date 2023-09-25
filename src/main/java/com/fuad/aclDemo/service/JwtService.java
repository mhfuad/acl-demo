package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtService {
    String generateAccessToken(CustomUserDetails userDetails);

    String generateRefreshToken(CustomUserDetails userDetails);

    Jws<Claims> validateToken(String token);
}
