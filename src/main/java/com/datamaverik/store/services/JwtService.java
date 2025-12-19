package com.datamaverik.store.services;

import com.datamaverik.store.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String secret;

    public String generateAccessToken(User user) {
        Long tokenExpiration = 300L;    //  5m

        return generateToken(user, tokenExpiration);
    }

    public String generateRefreshToken(User user) {
        Long tokenExpiration = 604800L;    //  7 days

        return generateToken(user, tokenExpiration);
    }

    private String generateToken(User user, Long tokenExpiration) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration * 1000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public boolean validateToken(String token) {
        try{
            var claims = getClaims(token);

            return claims.getExpiration().after(new Date());
        }
        catch (JwtException ex){
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getIdFromToken(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }
}
