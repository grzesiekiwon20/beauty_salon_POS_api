
package com.beautysalon.config;


import com.beautysalon.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userServiceImpl);
        provider.setPasswordEncoder(passwordEncoder);

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(provider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/error.html").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/simulateError").permitAll()
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/users/save").permitAll()
                        .requestMatchers("/products/byId/{productId}").permitAll()
                        .requestMatchers("/services/byId/{serviceId}").permitAll()
                        .requestMatchers("/products/byCategory/{categoryId}").permitAll()
                        .requestMatchers("/products/all").permitAll()
                        .requestMatchers("/services/byCategory/{categoryId}").permitAll()
                        .requestMatchers("/services/all").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/activities/addNew").permitAll()
                        .requestMatchers("/activities/save").hasRole("USER")
                        .requestMatchers("/products/addNew").hasRole("ADMIN")
                        .requestMatchers("/products/save").hasRole("ADMIN")
                        .requestMatchers("/address/**").hasRole("USER")
                        .requestMatchers("/account").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(form ->
                        form.
                                loginPage("/login")
                                .failureForwardUrl("/login-error")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/account", true)
                ).logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/").permitAll());
        return http.build();
    }

}
