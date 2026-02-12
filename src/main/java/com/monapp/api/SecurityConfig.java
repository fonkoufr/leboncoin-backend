package com.monapp.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // ✅ Active le support CORS en utilisant le bean corsConfigurationSource ci-dessous
            .cors(Customizer.withDefaults()) 
            
            // Désactive CSRF pour permettre les requêtes API (POST, DELETE, etc.)
            .csrf(csrf -> csrf.disable())
            
            // Autorise l'accès public à tous les endpoints pour le moment
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // ✅ Autorise ton site Vercel et ton environnement de test local
        configuration.setAllowedOrigins(List.of(
            "https://leboncoin-frontend.vercel.app", 
            "http://localhost:5173"
        )); 
        
        // Autorise les méthodes HTTP nécessaires pour FONKY'S S.A.
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Autorise les headers standards
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        
        // Permet l'envoi de cookies ou headers d'authentification si besoin
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}