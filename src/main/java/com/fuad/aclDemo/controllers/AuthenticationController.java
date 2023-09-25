package com.fuad.aclDemo.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    //private final AuthenticationServi
    @PostMapping("/login")
    public String login(){
        return "Login";
    }

}
