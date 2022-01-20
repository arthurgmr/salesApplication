package io.github.arthurgmr.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {
    
    @Value("${security.jwt.expires_token}")
    private String expires_token;

    @Value("${security.jwt.secret_token}")
    private String secret_token;

    //genarationg the token;
    public String genToken(UserEntity userData) {
        // transform expires_toke string to long(time);
        // the value was set in minutes (30);
        long minutes = Long.valueOf(expires_token);

        // get local time plus expires_token;
        LocalDateTime localDateExpires = LocalDateTime.now().plusMinutes(minutes);
        // transform dateTimeExp in Date because jjwt lib use Date;
        Instant timestamp = localDateExpires.atZone(ZoneId.systemDefault()).toInstant();
        Date dateExpires = Date.from(timestamp);

        // Mapping Hash
        // HashMap<String, Object> claims = new HashMap<>();
        // claims.put("useremail", "arthur.gmr@hotmail.com");
        // claims.put("roles", "admin");

        // return JWT token;
        return Jwts
                    .builder()
                    .setSubject(userData.getLogin())
                    .setExpiration(dateExpires)
                    // .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS512, secret_token)
                    .compact();
    }

    //decoding the token and getting claims;
    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                    .parser()
                    .setSigningKey(secret_token)
                    .parseClaimsJws(token)
                    .getBody();
    }

    //check token;
    public boolean checkToken (String token) {
        try {
            Claims claims = getClaims(token);
            Date dateExpires = claims.getExpiration();
            LocalDateTime localDateExpires = dateExpires
                                                .toInstant()
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateExpires);
        } catch (Exception e) {
            return false;
        }
    }

    //get login user;
    public String getLogin (String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }
}
