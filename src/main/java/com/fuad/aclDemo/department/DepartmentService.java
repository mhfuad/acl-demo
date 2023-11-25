package com.fuad.aclDemo.department;

import com.fuad.aclDemo.student.Student;
import com.fuad.aclDemo.teacher.Teacher;
import com.fuad.aclDemo.teacher.TeacherRequest;

import java.util.List;

public interface DepartmentService {
    List<Department> all();

    Department create(DepartmentRequest request);

    Department byId(Long id);

    Department update(Long id, DepartmentRequest request);

    void delete(Long id);
}
