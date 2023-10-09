package com.fuad.aclDemo.service.impl;

import com.fuad.aclDemo.config.properties.AuthenticationProperties;
import com.fuad.aclDemo.dto.CustomUserDetails;
import com.fuad.aclDemo.dto.request.AuthenticationRequest;
import com.fuad.aclDemo.dto.request.RefreshTokenRequest;
import com.fuad.aclDemo.dto.response.AuthenticationResponse;
import com.fuad.aclDemo.entity.Token;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.enums.TokenType;
import com.fuad.aclDemo.mapper.Mapper;
import com.fuad.aclDemo.repository.TokenRepository;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.service.AuthenticationService;
import com.fuad.aclDemo.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final AuthenticationProperties authenticationProperties;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(authentication.getName()).get();
        CustomUserDetails userDetails = Mapper.toCustomUserUserDetails(user);

        String accessToken = jwtService.generateAccessToken(userDetails);
        System.out.println("===================="+authentication.getName());
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        this.revokeUserToken(user, List.of(TokenType.ACCESS_TOKEN));
        this.saveUserToken(user, accessToken, TokenType.ACCESS_TOKEN);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokeType("Bearer")
                .expiresIn(authenticationProperties.getAccessToken().getExpirationInSeconds())
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        return null;
    }

    @Override
    @Transactional
    public void logout(String username) {
        User user = userRepository.findByUsername(username).get();

        revokeUserToken(user, Arrays.asList(TokenType.ACCESS_TOKEN, TokenType.REFRESH_TOKEN));
    }

    @Transactional
    public void revokeUserToken(User user, List<TokenType> tokenTypes){
        tokenTypes.forEach(tt -> {
            Optional<Token> token = tokenRepository.findByUserIdAndTokenType(user.getId(), tt);

            token.ifPresent(t -> {
                t.setRevoked(true);
                tokenRepository.save(t);
            });

        });
    }

    private void saveUserToken(User user, String token, TokenType tokenType){

        Token t = Token.builder()
                .token(token)
                .tokenType(tokenType)
                .user(user)
                .revoked(false)
                .build();

        tokenRepository.save(t);
    }
}
