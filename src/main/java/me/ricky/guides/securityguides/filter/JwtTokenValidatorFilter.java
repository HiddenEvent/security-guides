package me.ricky.guides.securityguides.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.ricky.guides.securityguides.constants.SecurityConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (null != jwt) {
            try {
                SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
                String username = String.valueOf(claims.get("username"));
                Set<String> authorities = new HashSet<>((List<String>) claims.get("authorities"));

                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, generateGrantedAuthorities(authorities));
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid JWT Token");
            }
        }
        filterChain.doFilter(request, response);
    }

    private List<GrantedAuthority> generateGrantedAuthorities(Set<String> authorities) {
        return authorities.stream()
                .map(authority -> (GrantedAuthority) () -> authority)
                .toList();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/user"); // /user 요청이 아닌, 모든 Endpoint 필터 적용
    }
}
