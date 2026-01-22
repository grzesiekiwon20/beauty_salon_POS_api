package com.beautysalon;


import com.beautysalon.activity.ActivityMapper;
import com.beautysalon.activity.ActivityRepository;
import com.beautysalon.cart.CartItemRepository;
import com.beautysalon.cart.CartService;
import com.beautysalon.role.Role;
import com.beautysalon.role.RoleRepository;
import com.beautysalon.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository, ActivityRepository activityRepository, CartItemRepository cartItemRepository, ActivityMapper activityMapper, CartService cartService) {
        return args -> {
            if (roleRepository.findByName("USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("USER");
                roleRepository.save(userRole);
            }
        };

    }

}
