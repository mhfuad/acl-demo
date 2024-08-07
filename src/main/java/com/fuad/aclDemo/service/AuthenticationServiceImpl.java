package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.request.AuthenticationRequest;
import com.fuad.aclDemo.dto.response.AuthenticationResponse;
import com.fuad.aclDemo.config.properties.AuthenticationProperties;
import com.fuad.aclDemo.config.CustomUserDetails;
import com.fuad.aclDemo.dto.request.RefreshTokenRequest;
import com.fuad.aclDemo.entity.Token;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.enums.TokenTypeEnum;
import com.fuad.aclDemo.mapper.Mapper;
import com.fuad.aclDemo.repository.TokenRepository;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.config.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        this.revokeUserToken(user, List.of(TokenTypeEnum.ACCESS_TOKEN));
        this.saveUserToken(user, accessToken, TokenTypeEnum.ACCESS_TOKEN);
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
        User user = userRepository.findByUsername(username).orElse(null);

        revokeUserToken(user, Arrays.asList(TokenTypeEnum.ACCESS_TOKEN, TokenTypeEnum.REFRESH_TOKEN));
    }

    @Transactional
    public void revokeUserToken(User user, List<TokenTypeEnum> tokenTypes){
        tokenTypes.forEach(tt -> {
            Optional<Token> token = tokenRepository.findByUserIdAndTokenType(user.getId(), tt);

            token.ifPresent(t -> {
                t.setRevoked(true);
                tokenRepository.save(t);
            });

        });
    }

    private void saveUserToken(User user, String token, TokenTypeEnum tokenType){

        Token t = Token.builder()
                .token(token)
                .tokenType(tokenType)
                .user(user)
                .revoked(false)
                .build();

        tokenRepository.save(t);
    }
}
