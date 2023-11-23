package com.fuad.aclDemo.teacher;

import com.fuad.aclDemo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
