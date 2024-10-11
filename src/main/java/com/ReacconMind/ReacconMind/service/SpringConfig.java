package com.ReacconMind.ReacconMind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SpringConfig(
        UserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder
    ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {
        http
            .csrf()
            .disable()
            .authorizeHttpRequests(registry -> {
                // Permite acceso a las rutas de Swagger y otras especificadas
                registry
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/ReacconMind/users/**"
                    )
                    .permitAll();
                registry.anyRequest().authenticated();
            })
            .oauth2Login(oauth2 ->
                oauth2
                    .defaultSuccessUrl(
                        "http://localhost:8080/api/oauth2/callback/google",
                        true
                    )
                    .failureUrl("/login?error=true")
            )
            .formLogin(form ->
                form
                    .defaultSuccessUrl(
                        "http://localhost:8080/doc/swagger-ui/index.html",
                        true
                    )
                    .failureUrl("/login?error=true")
            )
            .requiresChannel(channel -> channel.anyRequest().requiresInsecure()
            ); // Opción adicional para pruebas

        return http.build(); // Asegúrate de construir el objeto HttpSecurity
    }

    @Autowired
    @Profile("!test") // Deshabilitar esta configuración en el perfil de prueba
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {
        http
            .csrf()
            .disable()
            .authorizeHttpRequests(registry -> registry.anyRequest().permitAll()
            ); // Permitir acceso a todas las solicitudes
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }
}
