package com.fuad.aclDemo;

import com.fuad.aclDemo.repository.RoleRepository;
import com.fuad.aclDemo.user.UserRepository;
import com.fuad.aclDemo.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

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

	/*@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			ArrayList<Role> roles = new ArrayList<>();

			Role role = new Role();
			role.setName("ROLE_ADMIN");
			role.setDescription("ADMIN ROLE");
			roleRepository.save(role);

			Role role1 = new Role();
			role1.setName("ROLE_CUSTOMER");
			role1.setDescription("CUSTOMER");
			roleRepository.save(role1);

			*//*User user = User.builder()
					.firstName("Fuad")
					.lastName("Hasan")
					.username("fuad@gmail.com")
					.password(passwordEncoder.encode("123123"))
					.phoneNumber("01675944076")
					.roles(Collections.singleton(role))
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.enabled(true)
					.build();*//*
		};
	}*/
}
