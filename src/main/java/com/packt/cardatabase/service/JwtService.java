package com.packt.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATIONTIME = 86400000;
    static final String PREFIX = "Bearer";

//    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    static String secretString = "this_is_a_very_secure_256_bit_secret_key_123456";
    static final SecretKey key
            = new SecretKeySpec(secretString.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    public String getToken(String username) {
/*
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key).compact();

        return token;
 */
        return Jwts.builder()
                .subject(username)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token != null) {

            String user = Jwts.parser().verifyWith((SecretKey) key).build()
                    .parseSignedClaims(token.replace(PREFIX, "")).getBody().getSubject();

            if(user != null)
                return user;
        }

        return null;
    }
}
