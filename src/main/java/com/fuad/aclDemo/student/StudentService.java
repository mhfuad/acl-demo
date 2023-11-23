package com.fuad.aclDemo.student;

import java.util.List;

public interface StudentService {
    List<Student> all();

    Student create(StudentRequest request);

    Student byId(Long id);

    Student update(Long id, StudentRequest request);

    void delete(Long id);
}
