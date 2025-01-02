package com.fuad.aclDemo.controller;

import com.fuad.aclDemo.dto.response.RoleResponse;
import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.dto.response.RoleRequest;
import com.fuad.aclDemo.repository.RoleRepository;
import com.fuad.aclDemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService service;
    @Autowired
    RoleRepository repository;

    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(service.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody RoleRequest role){
        Role roleCreated = service.save(role);
        return ResponseEntity.ok(roleCreated);
    }
    @GetMapping("/roleAssignToUser/{userId}/{roleId}")
    public ResponseEntity<?> roleAssignToUser(@PathVariable Long userId, @PathVariable Long roleId) throws ChangeSetPersister.NotFoundException {
        try{
            boolean tr = service.roleAssignToUser(userId, roleId);
            if (!tr){
                return ResponseEntity.badRequest().body("Role Assign Error");
            }
            return ResponseEntity.ok("Role Assign Success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Not success"+ e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.ok("delete");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
