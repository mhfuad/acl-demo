package com.fuad.aclDemo;

import com.fuad.aclDemo.role.RoleRepository;
import com.fuad.aclDemo.role.Role;
import com.fuad.aclDemo.user.UserRepository;
import com.fuad.aclDemo.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class AclDemoApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AclDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
//			Map<String, String> roles = new HashMap<>();
//			roles.put("ROLE_ADMIN","ADMIN ROLE");
//			roles.put("ROLE_CUSTOMER","ADMIN ROLE");
//			roles.put("ROLE_STUDENT","ROLE STUDENT");
//			roles.put("ROLE_TEACHER","ROLE TEACHER");
//
//			for (Map.Entry<String, String> entry: roles.entrySet()){
//				Role role = new Role();
//				role.setName(entry.getKey());
//				role.setDescription(entry.getValue());
//				roleRepository.save(role);
//			}
		};
	}
}
