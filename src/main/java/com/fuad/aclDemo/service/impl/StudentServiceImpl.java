package com.fuad.aclDemo.service.impl;

import com.fuad.aclDemo.entity.Student;
import com.fuad.aclDemo.mapper.Mapper;
import com.fuad.aclDemo.repository.StudentRepository;
import com.fuad.aclDemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;


}
