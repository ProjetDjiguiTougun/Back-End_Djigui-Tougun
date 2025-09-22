//package com.example.backenddt.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtGestion {
//    private String cle_privee = "qwertyuiop";
//    public String genererToken(UserDetails userDetails){
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("roles",userDetails.getAuthorities().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
//                .signWith(SignatureAlgorithm.HS256,cle_privee)
//                .compact();
//    }
//
//    public String extraireUser(String token){
//        return Jwts.parser()
//                .setSigningKey(cle_privee)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//}
