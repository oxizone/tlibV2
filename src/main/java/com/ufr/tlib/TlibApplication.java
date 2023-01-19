package com.ufr.tlib;


import com.ufr.tlib.models.*;
import com.ufr.tlib.dataManagementServices.implementation.*;
import com.ufr.tlib.repository.IRoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.Month;


@SpringBootApplication(scanBasePackages= {"com.ufr.tlib"})
@RequiredArgsConstructor
public class TlibApplication implements CommandLineRunner {

	private final IRoleDao roleRepository;
	private final LocalService localService;

	private final ArtisanService artisanService;
	private final UserService userService;

	private final PrestationService prestationService;
	private final AddressService addressService;
	private final RDVService rdvService;


	public static void main(String[] args) {
		SpringApplication.run(TlibApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role userRole = Role.builder().roleName("ROLE_USER").build();
		Role managerRole = Role.builder().roleName("ROLE_MANAGER").build();
		Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();

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

		User admin = User.builder()
				.firstname("Prenom")
				.lastname("Nom")
				.email("admin@gmail.com")
				.username("admin")
				.password("admin")
				.phone("0102030405")
				.enabled(true)
				.role(adminRole)
				.build();


		Local salonCoiffure = Local.builder()
				.etat(Etat.ENABLE)
				.address("2 chemin des Tilleroyes, Besançon")
				.email("barber-tilleroyes@gmail.com")
				.phoneNumber("067263723")
				.description("Salon de coiffure")
				.service(Service.GARAGE)
				.name("L'Atelier")
				.build();

		Local salonCoiffure2 = Local.builder()
				.etat(Etat.ENABLE)
				.address("19 rue la République, Besançon")
				.email("salon@gmail.com")
				.phoneNumber("067263723")
				.description("Salon de coiffure")
				.service(Service.SALON_COIFFURE)
				.name("Barber Shop")
				.build();

		Artisan artisan =  Artisan.builder()
				.firstName("first")
				.lastName("last")
				.avatar("avat")
				.local(salonCoiffure)
				.build();


		Prestation coiffure = Prestation.builder()
				.price(18)
				.duration(45)
				.titre("Coiffure simple")
				.description("coiffure simple pour homme")
				.local(salonCoiffure)
				.build();

		Address address = Address.builder()
				.city("Besançon")
				.zipCode("25000")
				.local(salonCoiffure)
				.build();

		Address addres2 = Address.builder()
				.city("Besançon")
				.zipCode("25000")
				.local(salonCoiffure2)
				.build();

		RDV rdv = RDV.builder()
				.artisan(artisan)
				.prestation(coiffure)
				.client(user)
				.date(LocalDateTime.of(2023, Month.JANUARY, 23, 9, 30, 00, 000000))
				.build();

		roleRepository.save(managerRole);
		roleRepository.save(userRole);
		roleRepository.save(managerRole);
		roleRepository.save(adminRole);
		userService.addUser(user);
		userService.addUser(manager);
		userService.addUser(admin);

		localService.addLocal(salonCoiffure,manager.getUsername());
		localService.addLocal(salonCoiffure2,manager.getUsername());

		artisanService.addArtisan(artisan);

		prestationService.addPrestation(coiffure);
		addressService.addAddress(address);
		addressService.addAddress(addres2);

		rdvService.addRDV(rdv);
	}
}
