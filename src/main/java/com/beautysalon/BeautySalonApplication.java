package com.beautysalon;

import com.beautysalon.file.FileUtils;
import com.beautysalon.user.UserRepository;
import com.beautysalon.user.UserService;

import com.beautysalon.employee.EmployeeRepository;
import com.beautysalon.employee.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BeautySalonApplication {
	public static void main(String[] args) {

		SpringApplication.run(BeautySalonApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository, UserService service, EmployeeService employeeService, EmployeeRepository employeeRepository){
		return args -> {

		};
	}

}
