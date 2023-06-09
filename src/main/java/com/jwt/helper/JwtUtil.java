package com.jwt.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;


@Component
public class JwtUtil {

//    private String SECRET_KEY = "secret";
//    byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

	   private final String SECRET_KEY = "secret-key";
	    private final Key signingKey;

	    public JwtUtil() {
	        KeyGenerator keyGenerator;
	        try {
	            keyGenerator = KeyGenerator.getInstance(SignatureAlgorithm.HS256.getJcaName());
	            keyGenerator.init(256); // Specify the desired key size
	            signingKey = keyGenerator.generateKey();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("Failed to generate JWT signing key", e);
	        }
	    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    	return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();


    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

//    private String createToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                //.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//                .signWith(SignatureAlgorithm.HS256, keyBytes)
//                .setSubject("example")
//                .compact();
//    }
    
    @SuppressWarnings("deprecation")
	private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return extractUsername(token);
    }

//    public String getUsernameFromToken(String token) {
//        // Assuming getClaimsFromToken returns a Map or Claims object containing the username
//        Map<String, Object> claims = getClaimsFromToken(token);
//        String username = (String) claims.get("username");
//        return username;
//    }
    
    

}