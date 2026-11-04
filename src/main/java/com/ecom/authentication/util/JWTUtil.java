package com.ecom.authentication.util;


import com.ecom.authentication.dto.response.LoginResponse;
import com.ecom.authentication.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class JWTUtil {

    private final long EXPIRATION_TIME = 1000*60*60; // 1 hour
    private final String SECRET = "my-super-secret-key-that-is-long-enough-1234567890!@#";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    public LoginResponse generateToken(String userName, Role role) {

        return new LoginResponse(Jwts.builder()
                .subject(userName)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact(),"Bearer",EXPIRATION_TIME/60);

    }


    public String extractUsername(String jwtToken){
        return extractClaims(jwtToken).getSubject();
    }

    public String extractRole(String jwtToken){
        return extractClaims(jwtToken).get("role", String.class);
    }

    private Claims extractClaims(String jwtToken){
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return claims;
    }


    public boolean validateToken(String jwtToken) {
        try {
            extractClaims(jwtToken);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
//    private boolean isTokenExpired(String jwtToken){
//        return extractClaims(jwtToken).getExpiration().before(new Date());
//    }

}
