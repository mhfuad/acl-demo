package com.fuad.aclDemo.controllers;

import com.fuad.aclDemo.dto.request.StudentRequest;
import com.fuad.aclDemo.entity.Student;
import com.fuad.aclDemo.repository.StudentRepository;
import com.fuad.aclDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody StudentRequest request, BindingResult result){
        if (result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        Student student = new Student();
        student.setName(request.getName());
        student.setFatherName(request.getFatherName());
        student.setMotherName(request.getMotherName());
        student.setAddress(request.getAddress());
        try{
            Student repo = studentRepository.save(student);
            return ResponseEntity.ok(repo);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id){
        return ResponseEntity.ok(studentRepository.findById(id));
    }

    public ResponseEntity<?> update(@PathVariable Long id, StudentRequest request){
        try{
            Student s = studentRepository.findById(id).get();
            s.setName(request.getName());
            s.setFatherName(request.getFatherName());
            s.setMotherName(request.getMotherName());
            s.setAddress(request.getAddress());
            return ResponseEntity.ok(studentRepository.save(s));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Delete success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
