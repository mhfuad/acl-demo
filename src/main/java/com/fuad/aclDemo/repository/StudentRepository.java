package com.fuad.aclDemo.repository;

import com.fuad.aclDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
