package com.okankonur.sauron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home","/api/**","/public/**").permitAll()  // Allow public access to these URLs
                .anyRequest().authenticated()  // All other URLs require authentication
            )
            .formLogin(form -> form
                .loginPage("/login")  // Custom login page
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }

}
