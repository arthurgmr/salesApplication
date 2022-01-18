package io.github.arthurgmr.validation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.arthurgmr.domain.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {
    
    @Value("security.jwt.expires_token")
    private String expires_token;

    @Value("security.jwt.secret_token")
    private String secret_token;

    public String genToken(UserEntity userData) {
        // transform expires_toke string to long(time);
        // the value was set in minutes (30);
        long expLong = Long.valueOf(expires_token);

        // get local time plus expires_token;
        LocalDateTime dateTimeExp = LocalDateTime.now().plusMinutes(expLong);
        // transform dateTimeExp in Date because jjwt lib use Date;
        Instant instant = dateTimeExp.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        // return JWT token;
        return Jwts
                    .builder()
                    .setSubject(userData.getLogin())
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS512, secret_token)
                    .compact();
    }
}
