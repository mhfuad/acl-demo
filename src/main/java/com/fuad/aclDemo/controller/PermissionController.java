package com.fuad.aclDemo.controller;

import com.fuad.aclDemo.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    @Autowired
    PermissionRepository repository;
    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(repository.findAll());
    }
}
