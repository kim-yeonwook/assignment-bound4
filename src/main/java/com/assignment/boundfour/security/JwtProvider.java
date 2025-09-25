package com.assignment.boundfour.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtProvider {

    private final Key key;

    private final long expiredAt;

    public JwtProvider(String key, Long expiredAt) {
        byte[] secretKey = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(secretKey);
        this.expiredAt = expiredAt;
    }

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + expiredAt))
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication extractAuthentication(String token) {
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            List<GrantedAuthority> authorities = getRoles(claims)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            User principal = new User(claims.getSubject(), "", authorities);

            return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.debug("잘못된 JWT 서명입니다.", e);
            return null;
        } catch (ExpiredJwtException e) {
            log.debug("만료된 JWT 토큰입니다.", e);
            return null;
        } catch (UnsupportedJwtException e) {
            log.debug("지원되지 않는 JWT 토큰입니다.", e);
            return null;
        } catch (IllegalArgumentException e) {
            log.debug("JWT 토큰이 잘못되었습니다.", e);
            return null;
        }
    }

    public List<String> getRoles(Claims claims) {
        Object roles = claims.get("roles");
        if (roles instanceof List<?> list) {
            return list.stream().map(Object::toString).toList();
        }
        return List.of();
    }
}
