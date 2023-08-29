package passion3.BackEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/.well-known/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/stt").permitAll();
        http.authorizeHttpRequests().requestMatchers("/chatbot").permitAll();
        http.authorizeHttpRequests().requestMatchers("/").permitAll();
        http.authorizeHttpRequests().requestMatchers("/tts").permitAll();
        http.authorizeHttpRequests().requestMatchers("/food/*").permitAll();
        http.authorizeHttpRequests().requestMatchers("/swagger-ui/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/v3/api-docs/**").permitAll();
        http.authorizeHttpRequests().requestMatchers("/swagger-ui.html").permitAll();

//        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();
    }
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("voiceorder.example.com"); // Replace with your Android app's domain
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}