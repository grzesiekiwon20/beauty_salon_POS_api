package com.beautysalon;


import jakarta.servlet.http.HttpSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(HttpSession httpSession) {
        return args -> {
        };
    }

}
