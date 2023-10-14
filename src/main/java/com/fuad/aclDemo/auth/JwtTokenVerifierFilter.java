package com.fuad.aclDemo.auth;

import com.fuad.aclDemo.service.JwtService;
import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


//@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            System.out.println("No token provided");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try{
            Jws<Claims> claimsJws = jwtService.validateToken(token);
            System.out.println(claimsJws);
            Claims body = claimsJws.getBody();

            String username = (String) body.get("username");
            String authorities;
            if(body.get("authorities") == null){
                throw new MissingClaimException(claimsJws.getHeader(), body, "Token is invalid");
            } else {
                authorities = (String) body.get("authorities");
            }

            Set<SimpleGrantedAuthority> simpleGrantedAuthoritis = new HashSet<>();

            Arrays.asList(authorities.split(" "))
                    .forEach(a -> {
                            System.out.println(a);
                            simpleGrantedAuthoritis.add(new SimpleGrantedAuthority(a));
                        }
                    );

            System.out.println("0000000000000000000000"+authorities);
            System.out.println("0000000000000000000000"+simpleGrantedAuthoritis);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthoritis
            );
            System.out.println("authentication"+authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            System.out.println("Token is expired");
            throw new JwtException("Token is expired");

        } catch (JwtException e) {
            System.out.println("bal"+e.getMessage());
            throw new JwtException(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
