package com.fuad.aclDemo.department;

import com.fuad.aclDemo.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
