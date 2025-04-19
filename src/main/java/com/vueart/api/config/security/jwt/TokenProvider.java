package com.vueart.api.config.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TokenProvider {
    @Value("${jwt.secret}") //토큰 생성시 필요한 secretKey
    private String secretKeyStr;
    private SecretKey secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    private static final int WEB_EXPIRE_TIME = 24; // 24시간

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyStr.getBytes());
    }

    public String createToken(String userSpecification) {
        return Jwts.builder()
                .setSubject(userSpecification)
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusHours(WEB_EXPIRE_TIME)))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String validateTokenAndGetSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
