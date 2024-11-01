//package com.ssafy.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends WebAuthenticationFilter {
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//            if (jwtTokenProvider.validateToken(token)) {
//                String memberId = jwtTokenProvider.getMemberId(token);
//                UserDetails userDetails = // Load user by memberId;
//                SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
//                );
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
