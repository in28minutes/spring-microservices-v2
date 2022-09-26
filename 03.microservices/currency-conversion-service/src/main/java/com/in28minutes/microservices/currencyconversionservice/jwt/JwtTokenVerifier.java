package com.in28minutes.microservices.currencyconversionservice.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtManualConfig jwtManualConfig;

    public JwtTokenVerifier(SecretKey secretKey,
                            JwtManualConfig jwtManualConfig) {
        this.secretKey = secretKey;
        this.jwtManualConfig = jwtManualConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtManualConfig.getAuthorizationHeader());

        if (request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {

            if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtManualConfig.getTokenPrefix())) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorizationHeader.replace(jwtManualConfig.getTokenPrefix(), "");

            try {

                Jws<Claims> claimsJws = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token);

                Claims body = claimsJws.getBody();

                String username = body.getSubject();

                var authorities = (List<Map<String, String>>) body.get("authorities");

                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                        .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                        .collect(Collectors.toSet());

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        simpleGrantedAuthorities
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JwtException e) {
                logger.error(e.getMessage());
                throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
            }

            filterChain.doFilter(request, response);
        }
    }
}
