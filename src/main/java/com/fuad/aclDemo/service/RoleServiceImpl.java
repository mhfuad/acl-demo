package com.fuad.aclDemo.service;

import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.repository.RoleRepository;
import com.fuad.aclDemo.enums.RoleNameEnum;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.dto.response.RoleRequest;
import com.fuad.aclDemo.enums.UserTypeEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserRepository userRepository;
    private final RoleRepository repository;

    @Override
    public Role getRoleByUserType(UserTypeEnum userType) {
        RoleNameEnum roleName = switch (userType) {
            case CUSTOMER -> RoleNameEnum.ROLE_CUSTOMER;
            case ADMIN -> RoleNameEnum.ROLE_ADMIN;
            case TEACHER -> RoleNameEnum.ROLE_TEACHER;
            case STUDENT -> RoleNameEnum.ROLE_STUDENT;
            case USER -> RoleNameEnum.ROLE_USER;
        };

        return repository.findByName(roleName.toString()).orElseThrow(() -> new
                EntityNotFoundException("Role not found"));
    }

    @Override
    public List<Role> all() {
        return repository.findAll();
    }

    @Override
    public Role save(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return repository.save(role);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean roleAssignToUser(Long userId, Long roleId) {
        try{
            User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found."));
            Role role = repository.findById(roleId).orElseThrow(()-> new EntityNotFoundException("Role not found."));
            HashSet<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            user.setRoles(roleSet);
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }
}
