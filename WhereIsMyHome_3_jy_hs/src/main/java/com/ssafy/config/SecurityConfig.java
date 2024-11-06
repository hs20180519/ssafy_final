package com.ssafy.config;

import com.ssafy.member.model.service.MemberService;
import com.ssafy.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//        @Autowired
//        private JwtTokenProvider jwtTokenProvider;
//
//        @Autowired
//        private MemberService memberService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/member/login", "/api/member/join", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable
                )
                .httpBasic(withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/api/member/logout")
                        .logoutSuccessUrl("/api/member/login?logout=true")
                );
        return http.build();
    }
}

