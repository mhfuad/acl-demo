package com.fuad.aclDemo.config.jwt;

import com.fuad.aclDemo.config.properties.AuthenticationProperties;
import com.fuad.aclDemo.config.CustomUserDetails;
import com.fuad.aclDemo.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final AuthenticationProperties authenticationProperties;
    private final TokenRepository tokenRepository;

    @Override
    public String generateAccessToken(CustomUserDetails userDetails) {

        List<SimpleGrantedAuthority> authorityList = (List<SimpleGrantedAuthority>)userDetails.getAuthorities();

        String authorities = authorityList.stream().map(SimpleGrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Map<String, Object> tokenBody = new HashMap<>();
        tokenBody.put("id", userDetails.getId());
        tokenBody.put("firstName", userDetails.getFirstName());
        tokenBody.put("lastName", userDetails.getLastName());
        tokenBody.put("username", userDetails.getUsername());
        tokenBody.put("phoneNumber", userDetails.getPhoneNumber());
        tokenBody.put("verified", userDetails.isVerified());
        tokenBody.put("authorities", authorities);

        byte[] key = authenticationProperties.getSecretKey().getBytes();
        Integer expiration = authenticationProperties.getAccessToken().getExpirationInSeconds();

        return this.builderJwt(tokenBody, expiration, key);
    }

    @Override
    public String generateRefreshToken(CustomUserDetails userDetails) {
        Map<String, Object> tokenBody = new HashMap<>();
        tokenBody.put("id", userDetails.getId());
        tokenBody.put("username", userDetails.getUsername());

        byte[] key = authenticationProperties.getSecretKey().getBytes();
        Integer expiration = authenticationProperties.getRefreshToken().getExpirationInSeconds();
        return this.builderJwt(tokenBody, expiration, key);
    }

    @Override
    public Jws<Claims> validateToken(String token) {

        tokenRepository.findByTokenAndRevoked(token, false)
                .orElseThrow(()-> new JwtException("Token is invalid"));

        byte[] key = authenticationProperties.getSecretKey().getBytes();

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key))
                .build().parseClaimsJws(token);
    }

    private String builderJwt(Map<String, Object> body, Integer expiration, byte[] signingKey){
        try{
            return Jwts.builder()
                    .addClaims(body)
                    .setIssuedAt(new Date())
                    .setIssuer("self")
                    .setSubject((String) body.get("username"))
                    .setExpiration(java.sql.Timestamp.valueOf(LocalDateTime.now()
                            .plus(expiration, ChronoUnit.SECONDS)))
                    .signWith(Keys.hmacShaKeyFor(signingKey))
                    .compact();

        } catch (JwtException e){
            throw new JwtException(e.getMessage());
        }
    }
}
