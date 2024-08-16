package com.fuad.aclDemo;

import com.fuad.aclDemo.entity.Role;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.enums.UserTypeEnum;
import com.fuad.aclDemo.repository.RoleRepository;
import com.fuad.aclDemo.repository.UserRepository;
import com.fuad.aclDemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
			Map<String, String> roles = new HashMap<>();
			roles.put("ROLE_ADMIN","ADMIN Role");
			roles.put("ROLE_CUSTOMER","CUSTOMER role");
			roles.put("ROLE_STUDENT","STUDENT Role");
			roles.put("ROLE_TEACHER","TEACHER Role");
			roles.put("ROLE_USER","USER Role");

			for (Map.Entry<String, String> entry: roles.entrySet()){
				Role role = new Role();
				role.setName(entry.getKey());
				role.setDescription(entry.getValue());
				roleRepository.save(role);
			}

			User user = new User();
			user.setFirstName("fuad");
			user.setLastName("hasan");
			user.setUsername("fuad@gmail.com");
			user.setPhoneNumber("01675944076");
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			user.setVerified(true);
			user.setPassword(passwordEncoder.encode("123123"));
			userRepository.save(user);
		};
	}
}
