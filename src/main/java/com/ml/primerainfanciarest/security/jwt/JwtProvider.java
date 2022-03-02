package com.ml.primerainfanciarest.security.jwt;

import com.ml.primerainfanciarest.entities.UserPi;
import com.ml.primerainfanciarest.models.UserPiModel;
import com.ml.primerainfanciarest.security.models.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String geUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("malformed token");
        } catch (UnsupportedJwtException e) {
            logger.error("unsupported token");
        } catch (ExpiredJwtException e) {
            logger.error("expired token");
        } catch (IllegalArgumentException e) {
            logger.error("empty token");
            logger.error(token);
        } catch (SignatureException e) {
            logger.error("failed sign");
        }
        return false;
    }
}
