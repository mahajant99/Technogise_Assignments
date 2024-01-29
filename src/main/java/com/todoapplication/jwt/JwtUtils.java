package com.todoapplication.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import javax.crypto.SecretKey;

import com.liferay.portal.kernel.security.SecureRandom;

public class JwtUtils {

    private static final String SECRET_KEY = generateSecretKey();

    private static String generateSecretKey() {
        byte[] keyBytes = new byte[32];
        new SecureRandom().nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public static String generateToken(String username) {
        byte[] decodedSecret = Base64.getDecoder().decode(SECRET_KEY);
        Key secretKey = Keys.hmacShaKeyFor(decodedSecret);
        
        return Jwts.builder()
                .subject("Test JWT")
                .issuer(username)
                .signWith(secretKey)
                .compact();
    }

    public static boolean verifyJwt(String jwt, String expectedIssuer, String secret) {
        try {
            byte[] decodedSecret = Base64.getDecoder().decode(secret);
            SecretKey secretKey = Keys.hmacShaKeyFor(decodedSecret);

            JwtParser parser = Jwts.parser()
                    .verifyWith(secretKey)
                    .build();

            Jws<Claims> claimsJws = parser.parseSignedClaims(jwt);
            Claims claims = claimsJws.getPayload();

            if (claims.getIssuer().equals(expectedIssuer)) {
                return true;
            }

        } catch (JwtException e) {
            e.printStackTrace();
        }
        return false;
    }

}
