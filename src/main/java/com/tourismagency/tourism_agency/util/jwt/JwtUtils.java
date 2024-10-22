package com.tourismagency.tourism_agency.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private SecretKey key = Jwts.SIG.HS256.key().build();

    public String createToken(Authentication authentication) {

        String email = authentication.getName();

        Claims claims = (Claims) Jwts.claims().add("authorities", authentication.getAuthorities());

        return Jwts
                .builder()
                .subject(email)
                .claims(claims)
                .signWith(key)
                .expiration(new Date(System.currentTimeMillis() + 8640000))
                .issuedAt(new Date())
                .compact();
    }

    public Claims getClaims(String token) {
        return null;
    }
}
