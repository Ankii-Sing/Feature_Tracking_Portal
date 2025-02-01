package com.MyProject.Feature_Tracking_Portal.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {

    private static final String SECRET = "cabd8f6065f9628fc9e2d625ebde3679d4fef82db54e2075018652d396ada8555200c376b8c7640376dd016c11276980d97e1a4a59a521c5fd1453acf120d5f2ec1572b583b6a1d54238a025c218996eb12d4523edac661544ce51f62516b6ba96b5ff154c2fa45e9d60fbbd0b4b6ea53fc5cbdc60f653a3cc54833d9fd80c68fb5b80c3ad4622c58d59937191dbed5e07fe1e7acbd1ae7ab4d1f781279079bfc57b48dc437381075016b7345e47a6f7a79f5da785793acd610a03ab84363e99a4cf0f59c45ca5238f040be03df37ff533f282fe12364446e0b11c629a95cbb0117171bed6f57c764974f92d42170c853ae290d3077ff1765c2bd78c70324fd5";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extract single claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
    }
    public String genereateToken (UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token,UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    // extracting all the claims

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
