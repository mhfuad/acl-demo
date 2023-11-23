package com.fuad.aclDemo.teacher;

import com.fuad.aclDemo.student.Student;
import com.fuad.aclDemo.student.StudentRequest;

import java.util.List;

public interface TeacherService {
    List<Teacher> all();

    Teacher create(TeacherRequest request);

    Teacher byId(Long id);

    Teacher update(Long id, TeacherRequest request);

    void delete(Long id);
}
