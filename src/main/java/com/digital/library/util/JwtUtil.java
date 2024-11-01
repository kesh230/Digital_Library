package com.digital.library.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    private static final String secret_key="my-secret-key-my-secret-key-my-secret-key";
    private final SecretKey key=Keys.hmacShaKeyFor(secret_key.getBytes());

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        List<String> roles=userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", roles);                
        return this.createToken(claims,userDetails.getUsername());
    }


    private String createToken(Map<String,Object> claims,String subject){
      return Jwts.builder()
                 .claims(claims)
                 .subject(subject)
                 .issuedAt(Date.from(Instant.now()))
                 .expiration(Date.from(Instant.now().plus(10,ChronoUnit.HOURS)))
                 .signWith(key)
                 .compact();
    }
    public String extractUsername(String token){
        return this.extractAllClaims(token).getSubject();
    }
   
    public boolean isTokenExpired(String Token){
        return this.extractAllClaims(Token).getExpiration().before(new Date());
    }
    public boolean validateToken(String token,String username){
        final String tokenUsername=this.extractUsername(token);
        return (tokenUsername.equals(username)&& !this.isTokenExpired(token));
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }


    public Collection<SimpleGrantedAuthority> getRolesFromToken(String token) {
        Claims claims=extractAllClaims(token);
        return ((List<String>) claims.get("roles")).stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }

    
}
