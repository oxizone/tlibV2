package com.ufr.tlib;

import com.ufr.tlib.dataManagementServices.implementation.LocalService;
import com.ufr.tlib.dataManagementServices.implementation.UserService;
import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.Role;
import com.ufr.tlib.models.Service;
import com.ufr.tlib.models.User;
import com.ufr.tlib.repository.IRoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages= {"com.ufr.tlib"})
@RequiredArgsConstructor
public class TlibApplication implements CommandLineRunner {

	private final IRoleDao roleRepository;
	private final LocalService localService;
	private final UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(TlibApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role managerRole = Role.builder().roleName("ROLE_MANAGER").build();
		Role userRole = Role.builder().roleName("ROLE_USER").build();

		User manager = User.builder()
				.firstname("amine")
				.lastname("RABHI")
				.email("amine@gmail.com")
				.username("manager")
				.password("pass")
				.phone("0665653263")
				.enabled(true)
				.role(managerRole)
				.build();

		User user = User.builder()
				.firstname("user")
				.lastname("userlastname")
				.email("user@gmail.com")
				.username("testuser")
				.password("test")
				.phone("0665653263")
				.enabled(true)
				.role(userRole)
				.build();


		Local sallonCoiffure = Local.builder()
				.enabled(true)
				.address("19 rue la republique, besançcon")
				.email("sallon@gmail.com")
				.phoneNumber("067263723")
				.description("Sallon de coiffure")
				.service(Service.GARAGE)
				.name("L'atelier de coiffure")
				.build();

		Local sallonCoiffure2 = Local.builder()
				.enabled(true)
				.address("19 rue la republique, besançcon")
				.email("sallon@gmail.com")
				.phoneNumber("067263723")
				.description("Sallon de coiffure")
				.service(Service.SALON_COIFFURE)
				.name("L'atelier de coiffure")
				.build();


		roleRepository.save(managerRole);
		roleRepository.save(userRole);
		userService.addUser(manager);
		userService.addUser(user);


		localService.addLocal(sallonCoiffure,manager.getUsername());
		localService.addLocal(sallonCoiffure2,manager.getUsername());
	}
}
