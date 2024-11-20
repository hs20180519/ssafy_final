package com.ssafy.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.security.JwtFilter;
import com.ssafy.security.JwtTokenProvider;

import io.jsonwebtoken.lang.Arrays;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;

	private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(corsCustomizer -> {
				CorsConfiguration corsConfig = new CorsConfiguration();
				corsConfig.setAllowCredentials(true); // 자격증명 요청 허용
				corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:8080".split(","))); // 프론트엔드 주소
				corsConfig.addAllowedHeader("*"); // 모든 헤더 허용
				corsConfig.addAllowedMethod("*"); // 모든 메서드 허용
				corsConfig.addExposedHeader("Authorization"); // Authorization 헤더를 클라이언트에 노출
				UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
				source.registerCorsConfiguration("/**", corsConfig); // 모든 패턴에 대하여 CORS 정책 적용

				corsCustomizer.configurationSource(source);
			})
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/auth/*", "/css/**", "/js/**", "/images/**",
					"/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
					"/swagger-resources/**", "/swagger-ui/**")

				.permitAll()
				.anyRequest().authenticated())
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(withDefaults())
			.logout(logout -> logout
				.logoutUrl("/api/member/logout")
				.logoutSuccessUrl("/api/member/login?logout=true"))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
