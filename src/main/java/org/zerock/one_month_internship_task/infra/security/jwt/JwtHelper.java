package org.zerock.one_month_internship_task.infra.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.zerock.one_month_internship_task.domain.user.dto.type.AuthoritiesType;
import org.zerock.one_month_internship_task.infra.security.UserPrincipal;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class JwtHelper {
    @Value("${auth.jwt.issuer}")
    private String issuer;
    @Value("${auth.jwt.secretKey}")
    private String secretKey;
    @Value("${auth.jwt.accessTokenExpirationHour}")
    private Long accessTokenExpirationHour;

    private static final Logger log = LoggerFactory.getLogger(JwtHelper.class);


    public Boolean validateToken(String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(token)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public String generateAccessToken(
            String subject,
            AuthoritiesType role
    ) {
        return generateToken(subject, role, Duration.ofHours(accessTokenExpirationHour));
    }

    private String generateToken(
            String subject,
            AuthoritiesType role,
            Duration expirationPeriod
    ) {
        Instant now = Instant.now();

        return Jwts
                .builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expirationPeriod)))
                .claim("authorities", Map.of("role", role))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public UserPrincipal getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("authorities").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return (UserPrincipal) new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities).getPrincipal();
    }
}
