package com.spring.shelfLife.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration-ms}")
    private long accessTokenExpiration;

    @Value("${app.jwt.refresh-expiration-ms}")
    private long refreshTokenExpiration;

    private String buildToken(UserDetails userDetails, long expirationMs, String type) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(Map.of("type",type))
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(signingKey())
                .compact();
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private <T>T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token).getPayload();
        return claimsResolver.apply(claims);
    }

    private boolean isExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean isRefresh(String token) {
        return "refresh".equals(extractTokenType(token));
    }


    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(userDetails, accessTokenExpiration, "access");
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(userDetails, refreshTokenExpiration, "refresh");
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractTokenType(String token) {
        return extractClaim(token,claims->claims.get("type", String.class));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }



}
