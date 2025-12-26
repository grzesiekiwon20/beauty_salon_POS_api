
package com.beautysalon.security;


import com.beautysalon.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserServiceImpl userServiceImpl;

    public SecurityConfig(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/categories/").permitAll()
                        .requestMatchers("/categories/subCategory").permitAll()
                        .requestMatchers("/products/").permitAll()
                        .requestMatchers("/products/{productId}").permitAll()
                        .requestMatchers("/products/byCategoryId/{categoryId}").permitAll()
                        .requestMatchers("/categories/{categoryId}").permitAll()
                        .requestMatchers("/services/all").permitAll()
                        .requestMatchers("/services/byCategory").permitAll()
                        .requestMatchers("/services/serviceId/{id}").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/cartItem/**").permitAll()
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/address/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .userDetailsService(userServiceImpl)
                .formLogin(withDefaults());

        return http.build();
    }


}
