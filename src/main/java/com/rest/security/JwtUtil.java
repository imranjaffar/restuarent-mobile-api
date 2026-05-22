package com.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 🔥 Secret Key (must be 32+ chars)
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";

    // 🔥 Token Expiration (10 hours)
    private static final long EXPIRATION =
            1000 * 60 * 60 * 10;

    // 🔥 Signing Key
    private static final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    // =========================================
    // 🔐 Generate JWT Token
    // =========================================
    public static String generateToken(
            String username,
            String role
    ) {

        return Jwts.builder()

                .setSubject(username)

                .claim("role", role)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION
                        )
                )

                .signWith(
                        key,
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    // =========================================
    // 👤 Extract Username
    // =========================================
    public static String extractUsername(
            String token
    ) {

        return extractAllClaims(token)
                .getSubject();
    }

    // =========================================
    // 👮 Extract Role
    // =========================================
    public static String extractRole(
            String token
    ) {

        return extractAllClaims(token)
                .get("role", String.class);
    }

    // =========================================
    // 📦 Extract All Claims
    // =========================================
    public static Claims extractAllClaims(
            String token
    ) {

        return Jwts.parserBuilder()

                .setSigningKey(key)

                .build()

                .parseClaimsJws(token)

                .getBody();
    }

    // =========================================
    // ✅ Validate Token
    // =========================================
    public static boolean validate(
            String token
    ) {

        try {

            extractAllClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    // =========================================
    // 🔑 Get Signing Key
    // =========================================
    public static Key getKey() {
        return key;
    }
}