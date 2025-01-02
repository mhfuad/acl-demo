package com.fuad.aclDemo.home;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @GetMapping("/home")
    public String homePage(){
        return "Hello World";
    }


}
