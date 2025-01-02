package com.fuad.aclDemo.service;

import com.fuad.aclDemo.dto.request.DepartmentRequest;
import com.fuad.aclDemo.entity.Department;
import com.fuad.aclDemo.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private final DepartmentRepository repo;

    @Override
    public List<Department> all() {
        return repo.findAll();
    }

    @Override
    public Department create(DepartmentRequest request) {
        Department v = new Department();
        v.setName(request.getName());
        return repo.save(v);
    }

    @Override
    public Department byId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Department update(Long id, DepartmentRequest request) {
        Department v = repo.findById(id).get();
        v.setName(request.getName());
        return repo.save(v);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }


}
