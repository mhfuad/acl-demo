package com.fuad.aclDemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/home")
public class HomeController {
    @GetMapping("/good")
    public String homePage(){
        return "Hello World";
    }

    @GetMapping("/bal")
    public String bal(){
        return "bal";
    }
}
