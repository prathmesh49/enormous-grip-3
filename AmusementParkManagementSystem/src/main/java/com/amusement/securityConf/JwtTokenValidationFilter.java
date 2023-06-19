package com.amusement.securityConf;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(SecurityConstants.JWT_HEADER);
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {

            authorizationHeader = authorizationHeader.trim();

            try {

                jwt = authorizationHeader.substring(7); // extracting the word "Bearer"

                JwtParser jwtParser = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes()))
                        .build();

                Jwt<Header<?>, Claims> token = (Jwt<Header<?>, Claims>) jwtParser.parse(jwt);

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        token.getBody().getSubject(),
                        null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(token.getBody().get("authorities").toString())
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            catch (MalformedJwtException e) {

                // Below response is not being sent
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token: " + jwt);
                return;
            }
            catch (ExpiredJwtException e) {

                // Below response is not being sent
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Expired JWT token: " + jwt);
                return;
            }
            catch (IllegalArgumentException e) {

                // Below response is not being sent
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "JWT cannot be null or empty or only whitespace");
                return;
            }
            catch (Exception e) {

                // Below response is not being sent
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

