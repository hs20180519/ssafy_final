package com.ssafy.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

	private final JwtTokenProvider jwtTokenProvider;
	private static final String AUTHORIZATION_HEADER = "Authorization";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		String token = resolveToken(httpServletRequest);

		if (token != null && jwtTokenProvider.validateToken(token)) {
			String memberId = jwtTokenProvider.getMemberIdFromToken(token);
			String role = jwtTokenProvider.getMemberRoleFromToken(token);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				memberId, null, mapRoleToAuthority(role));

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(httpServletRequest, servletResponse);
	}

	// JWT에서 role 추출
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	// role -> Authority
	private Collection<? extends GrantedAuthority> mapRoleToAuthority(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}
}
