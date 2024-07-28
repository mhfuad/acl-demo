package com.fuad.aclDemo.controller;

import com.fuad.aclDemo.common.ResponseObject;
import com.fuad.aclDemo.dto.request.UserRequest;
import com.fuad.aclDemo.dto.response.UserResponse;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/create")

    public ResponseEntity<?> registerUser(@RequestBody UserRequest request){
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("ok");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error: "+e);
        }
    }
//    @PostMapping("/create")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult result){
//        log.info("Received user registration request: {}", request);
//        if (result.hasErrors()){
//            Map<String,String> errors = new HashMap<>();
//            for (FieldError error: result.getFieldErrors()){
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        return ResponseEntity.ok(userService.registerUser(request));
//    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user_create')")
    public ResponseEntity<?> byId(@PathVariable Long id){
        try{
            User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+ e);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseObject<Page<UserResponse>> getAllUsers(@PageableDefault(sort = "id",
                                                            direction = Sort.Direction.DESC
                                                            )Pageable pageable
                                                        ){
        return ResponseObject.<Page<UserResponse>>builder()
                .status(ResponseObject.ResponseStatus.SUCCESSFUL)
                .data(userService.getAllUsers(pageable))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserRequest request) {
        try{
            User user = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found id: "+ id));
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPhoneNumber(request.getPhoneNumber());
            User updateUser = repository.save(user);
            return ResponseEntity.ok(updateUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Update error: "+e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id){
        try{
            repository.deleteUserRole(id);
            User user = repository.findById(id).get();
            repository.delete(user);
            return ResponseEntity.ok("delete success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("delete error: "+ e);
        }
    }
}
