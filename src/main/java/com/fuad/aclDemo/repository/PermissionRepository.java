package com.fuad.aclDemo.repository;

import com.fuad.aclDemo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
