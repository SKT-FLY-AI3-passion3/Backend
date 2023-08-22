package me.shinsunyoung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/.well-known/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/convert").permitAll();
        http.authorizeHttpRequests().requestMatchers("/").permitAll();
        http.authorizeHttpRequests().requestMatchers("/api/persons/insert").permitAll();
//        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }
}