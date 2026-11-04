package com.ecom.authentication.filter;

import com.ecom.authentication.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }
                String jwtToken = authHeader.substring(7);
                String userName = jwtUtil.extractUsername(jwtToken);
                String role = jwtUtil.extractRole(jwtToken);

                List<GrantedAuthority> authorities = List.of(
                        new SimpleGrantedAuthority(
                                role.startsWith("ROLE_") ? role : "ROLE_" + role
                        ));

                if (jwtUtil.validateToken(jwtToken)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                    filterChain.doFilter(request, response);
                }
            }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod())
                && "/v1/login".equals(request.getServletPath());
    }

}
