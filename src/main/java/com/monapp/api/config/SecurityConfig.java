package com.monapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactivé pour permettre les tests API
            .cors(Customizer.withDefaults()) // Active le CORS avec tes réglages Controller
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/annonces/**").permitAll() // ✅ Tout le monde peut voir les annonces
                .anyRequest().authenticated() // 🔒 Le reste demande une connexion
            )
            .httpBasic(Customizer.withDefaults()); // Permet l'authentification simple

        return http.build();
    }
}