package com.rollerspeed.rollerspeed.Config;

import com.rollerspeed.rollerspeed.Service.DetallesUsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final DetallesUsuarioService detallesUsuarioService;

    public SecurityConfig(DetallesUsuarioService detallesUsuarioService) {
        this.detallesUsuarioService = detallesUsuarioService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Páginas públicas (accesibles sin iniciar sesión)
                .requestMatchers(
                    "/user/registro",
                    "/user/guardar",
                    "/user/login",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/mision",
                    "/vision",
                    "/valores",
                    "/servicios",
                    "/eventos",
                    "/galeria"
                ).permitAll()

                // Solo ADMIN puede manejar pagos, estudiantes e instructores
                .requestMatchers(
                    "/pagos/**",
                    "/estudiantes/**",
                    "/instructores/**"
                ).hasRole("ADMIN")

                // Estudiantes, instructores y admin pueden ver calendario y registrar clases
                .requestMatchers(
                    "/calendario/**",
                    "/clases/**"
                ).hasAnyRole("ESTUDIANTE", "INSTRUCTOR", "ADMIN")

                // Página principal e index accesible a cualquier autenticado
                .requestMatchers("/", "/index", "/home").authenticated()

                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )

            // Configuración del login
            .formLogin(form -> form
                .loginPage("/user/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/user/login?error=true")
                .permitAll()
            )

            // Configuración del logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/user/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // Página personalizada para error 403 (acceso denegado)
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/error-403")
            )

            // Configuración del servicio de usuarios
            .userDetailsService(detallesUsuarioService)

            // CSRF desactivado solo si usas formularios Thymeleaf sin token
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
