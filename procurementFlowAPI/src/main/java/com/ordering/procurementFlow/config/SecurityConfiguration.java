package com.ordering.procurementFlow.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.ordering.procurementFlow.Models.Permission.*;
import static com.ordering.procurementFlow.Models.Role.ADMIN;
import static com.ordering.procurementFlow.Models.Role.PROCUREMENT_OFFICER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/email/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole(ADMIN.name())
                .requestMatchers(OPTIONS,"/api/admin/**").permitAll()
                .requestMatchers(POST,"/api/admin/**").hasAuthority(ADMIN_CREATE.name())
                .requestMatchers(GET,"/api/admin/**").hasAuthority(ADMIN_READ.name())
                .requestMatchers(PUT,"/api/admin/**").hasAuthority(ADMIN_UPDATE.name())
                .requestMatchers(DELETE,"/api/admin/**").hasAuthority(ADMIN_DELETE.name())
                .requestMatchers("/api/procurement/**").hasRole(PROCUREMENT_OFFICER.name())
                .requestMatchers(GET,"/api/procurement/**").hasAuthority(PROCUREMENT_OFFICER_READ.name())
                .requestMatchers(POST,"/api/procurement/**").hasAuthority(PROCUREMENT_OFFICER_CREATE.name())
                .requestMatchers(PUT,"/api/procurement/**").hasAuthority(PROCUREMENT_OFFICER_UPDATE.name())
                .requestMatchers(DELETE,"/api/procurement/**").hasAuthority(PROCUREMENT_OFFICER_DELETE.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(withDefaults());


        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS","PUT","DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Specify allowed headers here
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
