package com.fuad.aclDemo.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(service.all());
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody RoleRequest role){

        Role roleCreated = service.save(role);
        return ResponseEntity.ok(roleCreated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.ok("delete");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
