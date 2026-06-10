package com.monapp.api.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityHeadersConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Add Security Headers Interceptor
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                // Prevent MIME type sniffing
                response.setHeader("X-Content-Type-Options", "nosniff");
                
                // Prevent clickjacking attacks
                response.setHeader("X-Frame-Options", "DENY");
                
                // Enable XSS protection in older browsers
                response.setHeader("X-XSS-Protection", "1; mode=block");
                
                // Enforce HTTPS
                response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
                
                // Control referrer information
                response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
                
                // Restrict which features can be used in the browser
                response.setHeader("Permissions-Policy", "geolocation=(), microphone=(), camera=()");
                
                return true;
            }
        });
        
        // Add Rate Limiting Interceptor
        registry.addInterceptor(new RateLimitingInterceptor())
            .addPathPatterns("/api/**");
    }
}

