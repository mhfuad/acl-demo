package com.fuad.aclDemo.service;

import com.fuad.aclDemo.entity.Teacher;
import com.fuad.aclDemo.dto.request.TeacherRequest;

import java.util.List;

public interface TeacherService {
    List<Teacher> all();

    Teacher create(TeacherRequest request);

    Teacher byId(Long id);

    Teacher update(Long id, TeacherRequest request);

    void delete(Long id);
}
