package com.tourismagency.tourism_agency.configuration.app.security;

import com.tourismagency.tourism_agency.service.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {

                    //documentacion
                    request.requestMatchers("/documentation").permitAll();
                    request.requestMatchers("/swagger-ui/**").permitAll();
                    request.requestMatchers("/v3/api-docs/**").permitAll();

                    //user
                    request.requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/api/v1/register").permitAll();

                    //customer
                    request.requestMatchers(HttpMethod.GET, "/api/v1/customer/{id}").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.GET, "/api/v1/customers").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.POST, "/api/v1/customer").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.PUT, "/api/v1/customer/{id}").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.DELETE, "/api/v1/customer/{id}").hasRole("ADMIN");

                    //tourist service
                    request.requestMatchers(HttpMethod.GET, "/api/v1/service/{id}").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.GET, "/api/v1/services").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.POST, "/api/v1/customer").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.PUT, "/api/v1/service/{id}").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.DELETE, "/api/v1/service/{id}").hasRole("ADMIN");

                    //tourist package
                    request.requestMatchers(HttpMethod.GET, "/api/v1/package/{id}").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.GET, "/api/v1/packages").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.POST, "/api/v1/package").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.PUT, "/api/v1/package/{id}").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.DELETE, "/api/v1/package/{id}").hasRole("ADMIN");

                    //sale
                    request.requestMatchers(HttpMethod.GET, "/api/v1/sale/{id}").hasAnyRole("ADMIN", "CUSTOMER");
                    request.requestMatchers(HttpMethod.GET, "/api/v1/sales").hasAnyRole("ADMIN", "CUSTOMER");
                    request.requestMatchers(HttpMethod.POST, "/api/v1/sale").hasAnyRole("ADMIN", "CUSTOMER");
                    request.requestMatchers(HttpMethod.PUT, "/api/v1/sale/{id}").hasAnyRole("ADMIN", "CUSTOMER");
                    request.requestMatchers(HttpMethod.DELETE, "/api/v1/sale/{id}").hasAnyRole("ADMIN", "CUSTOMER");

                    request.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    };

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
