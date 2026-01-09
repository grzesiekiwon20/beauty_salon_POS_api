package com.beautysalon;


import com.beautysalon.activity.ActivityMapper;
import com.beautysalon.activity.ActivityRepository;
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
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository, ActivityRepository activityRepository, ActivityMapper activityMapper, CartService cartService) {
        return args -> {
            if (roleRepository.findByName("ADMIN").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ADMIN");
                roleRepository.save(userRole);
            }
//            UserEntity userEntity = userRepository.findByUsername("gabriela10");
//            Role role = roleRepository.findByName("EMPLOYEE").orElseThrow();
//
//            Set<Role> roleSet = userEntity.getRoles();
//            roleSet.add(role);
//            userEntity.setRoles(roleSet);
//            userRepository.save(userEntity);


        };
    }

}
