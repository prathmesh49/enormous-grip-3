package com.amusement.securityConf;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(SecurityConstants.JWT_HEADER);

        if(authorizationHeader != null) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String authorities = authentication.getAuthorities()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            String token = Jwts.builder()
                    .setId(UUID.randomUUID().toString())
                    .setIssuer("Amusement Park (Next Level Project)") // ????
                    .setSubject(authentication.getName())
                    .claim("authorities", authorities)
                    .setAudience("Customer")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + SecurityConstants.JWT_TOKEN_VALIDITY_IN_SECONDS * 1000))
                    .signWith(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes()))
                    .compact();

            response.setHeader(SecurityConstants.JWT_HEADER, "Bearer " + token);
        }

        filterChain.doFilter(request, response);
    }
}

