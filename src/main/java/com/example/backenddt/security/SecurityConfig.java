package com.example.back_end_DT.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private Intercepteur intercepteurJwt;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .authorizeHttpRequests(
                    auth -> auth
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/administrateur/**").hasRole("ROLE_ADMIN")
                            .requestMatchers("/association/**").hasRole("ROLE_ASSICIATION")
                            .requestMatchers("/parrain/**").hasRole("ROLE_PARRAIN")
                            .requestMatchers("/parent/**").hasRole("ROLE_PARENT")
                            .anyRequest().authenticated()
                )
                .addFilterBefore(intercepteurJwt, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
