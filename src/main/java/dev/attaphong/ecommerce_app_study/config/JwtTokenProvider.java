package dev.attaphong.ecommerce_app_study.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final long tokenValidity;
    @Getter
    private final String tokenPrefix;
    private final SecretKey secretKey;

    public JwtTokenProvider(
            @Value("${jwt.token.validity}") long tokenValidity,
            @Value("${jwt.signing.key}") String signingKey,
            @Value("${jwt.token.prefix}") String tokenPrefix
    ){
        this.tokenValidity = tokenValidity;
        this.tokenPrefix = tokenPrefix;
        secretKey = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username){
        long currentTime = System.currentTimeMillis();
        return tokenPrefix + " " +
                Jwts.builder()
                        .subject(username)
                        .issuedAt(new Date(currentTime))
                        .expiration(new Date(currentTime+tokenValidity))
                        .signWith(secretKey)
                        .compact();
    }

    public String validateAndGetUsername(String token) {
        Jws<Claims> jws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        return jws.getPayload().getSubject();
    }

}
