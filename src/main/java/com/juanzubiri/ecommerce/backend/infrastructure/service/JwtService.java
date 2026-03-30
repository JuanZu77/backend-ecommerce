package com.juanzubiri.ecommerce.backend.infrastructure.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.juanzubiri.ecommerce.backend.infrastructure.config.JwtConfig;
import com.juanzubiri.ecommerce.backend.infrastructure.jwt.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    private final JwtConfig jwtConfig;
    private final CustomUserDetailService customUserDetailService;

    public JwtService(JwtConfig jwtConfig, CustomUserDetailService customUserDetailService) {
        this.jwtConfig = jwtConfig;
        this.customUserDetailService = customUserDetailService;
    }

    //verifica presencia del token en cada requests
    public boolean tokenExists(HttpServletRequest request) {
        String header = request.getHeader(Constants.HEADER_AUTHORIZATION);
        return header != null && header.startsWith(Constants.TOKEN_BEARER_PREFIX);
    }

    //valida integridad, firma y expiración
    public Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(Constants.HEADER_AUTHORIZATION)
                .replace(Constants.TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    //registra usuario en Spring Security
    public void setAuthentication(Claims claims) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    //crea el token en el login
    public String generateToken(String username) {
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(
                SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","))
        );

        String token = Jwts.builder()
                .setId("ecommerce")
                .setSubject(username)
                .claim("authorities", authorityList.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hs
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)))
                .compact();

        return Constants.TOKEN_BEARER_PREFIX + token;
    }
}

/*package com.juanzubiri.ecommerce.backend.infrastructure.service;

import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.juanzubiri.ecommerce.backend.infrastructure.config.JwtConfig;
import com.juanzubiri.ecommerce.backend.infrastructure.jwt.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public boolean tokenExists(HttpServletRequest request) {
        String header = request.getHeader(Constants.HEADER_AUTHORIZATION);
        return header != null && header.startsWith(Constants.TOKEN_BEARER_PREFIX);
    }

    public Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(Constants.HEADER_AUTHORIZATION)
                .replace(Constants.TOKEN_BEARER_PREFIX, "");

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
    
    public void setAuthentication(Claims claims, CustomUserDetailService customUserDetailService) {

    	UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());
    	
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    	SecurityContextHolder.getContext().setAuthentication(authenticationToken);
       
    }
}*/