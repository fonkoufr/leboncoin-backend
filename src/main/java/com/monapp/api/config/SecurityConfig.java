package com.monapp.api.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Désactivation CSRF (indispensable pour que Vercel puisse envoyer des POST/PUT)
            .csrf(csrf -> csrf.disable())
            
            // 2. Configuration CORS parfaite pour laisser passer ton Front-End
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("*")); // Autorise toutes les origines
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                return config;
            }))
            
            // 3. Autorisations : On ouvre TOUTES les portes temporairement
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() 
            )
            
            // 4. LA SOLUTION : Désactivation explicite des pop-ups de mot de passe par défaut
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }
}