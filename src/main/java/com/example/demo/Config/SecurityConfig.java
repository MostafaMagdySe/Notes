package com.example.demo.Config;

import com.example.demo.Services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService customUserDetailsService;
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtFilter jwtFilter){
        this.customUserDetailsService= customUserDetailsService;
        this.jwtFilter=jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing (not recommended in production)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/v3/api-docs/**", // OpenAPI documentation
                                "/swagger-ui/**", // Swagger UI resources
                                "/swagger-ui.html", // Swagger UI HTML
                                "/register", // Your public endpoint
                                "/login"
                        ).permitAll() // Allow unauthenticated access to /register
                        .anyRequest().authenticated()) // Require authentication for other endpoints
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // session to maintain the logged-in state
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(customUserDetailsService);


        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
}