package com.beautysalon;

import com.beautysalon.controller.dto.UserRequest;
import com.beautysalon.repository.UserRepository;
import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.users.LoginDetails;
import com.beautysalon.repository.model.users.User;
import com.beautysalon.repository.model.UserType;
import com.beautysalon.service.impl.GeneralServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BeautySalonApplication {
	public static void main(String[] args) {

		SpringApplication.run(BeautySalonApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(GeneralServiceImpl generalService){
		return args -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


		};
	}

}
