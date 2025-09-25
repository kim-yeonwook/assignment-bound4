package com.assignment.boundfour.security.configuration;

import com.assignment.boundfour.security.filter.JwtFilter;
import com.assignment.boundfour.security.handler.JwtAccessDeniedHandler;
import com.assignment.boundfour.security.handler.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtFilter jwtFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                .exceptionHandling(
                        exception -> exception
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/projects/token").permitAll()
                        .anyRequest().authenticated()
                )


                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

}
