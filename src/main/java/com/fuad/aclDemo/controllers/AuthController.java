package com.fuad.aclDemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {
    @PostMapping("/login")
    public String login(){
        return "Login";
    }

//    @GetMapping("/bal")
//    public String bal(){
//        return "bal";
//    }
}
