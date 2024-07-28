package com.fuad.aclDemo.repository;

import com.fuad.aclDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(nativeQuery = true, value = "delete from users_roles where user_id = :userId")
    boolean deleteUserRole(Long userId);
}
