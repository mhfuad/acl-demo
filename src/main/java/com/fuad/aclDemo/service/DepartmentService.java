package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.request.DepartmentRequest;
import com.fuad.aclDemo.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> all();

    Department create(DepartmentRequest request);

    Department byId(Long id);

    Department update(Long id, DepartmentRequest request);

    void delete(Long id);
}
