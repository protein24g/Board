package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProcess")
                        .permitAll()
                );

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // 최대 세션 허용 수
                        .maxSessionsPreventsLogin(true) // false = 기존 세션 만료
                );

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId() // 사용자가 인증 성공하면, 기존 사용자의 세션에 Session ID만 변경
                );

        http
                .logout((auth) -> auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logoutProcess")
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
