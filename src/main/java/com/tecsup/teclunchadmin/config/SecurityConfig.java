package com.tecsup.teclunchadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        // Permite el acceso sin autenticación a rutas específicas
                        .requestMatchers("/api/**", "/test").permitAll() // Rutas públicas para las APIs
                        .requestMatchers("/", "/login", "/login/oauth2/code/google", "/error").permitAll() // Rutas públicas de login y redirección de Google OAuth2
                        .anyRequest().authenticated() // Requiere autenticación para el resto de las rutas
                )
                .oauth2Login(oauth2 -> oauth2 // Configuración de OAuth2 con Google
                        .loginPage("/login") // Página personalizada de login
                        .defaultSuccessUrl("/home", true) // Redirige a la página principal después de login exitoso
                        .failureUrl("/error") // Redirige en caso de error
                )
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF (recomendado solo para pruebas locales, puedes habilitarlo después de validar)
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redirige a la página de login después de cerrar sesión
                );

        return http.build();
    }
}
