package com.oliveiradevs.petbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desativa a proteção CSRF (necessário para testes com POST/PUT)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite acesso público a todos os endpoints
                );

        return http.build();
    }
}
