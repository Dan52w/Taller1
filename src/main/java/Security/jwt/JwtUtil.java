package Security.jwt;

import Security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.expirationMs}")
    private String jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetails userDetails = (UserDetailsImpl)authentication.getPrincipal();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parse(authToken);
            return true;
        }catch(MalformedJwtException e){
            log.error("Invalid JWT token: {}", e.getMessage());
        }catch(ExpiredJwtException e){
            log.error("Expired JWT token: {}", e.getMessage());
        }catch(UnsupportedJwtException e){
            log.error("JWT Token is supported: {}", e.getMessage());
        }catch(IllegalArgumentException e){
            log.error("JWT claims string is empty: {}", e.getMessage());
        }catch(SignatureException e){
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(key()).build()
                .parseClaimsJwt(token).getPayload().getSubject();
    }

    private Key key(){//Documentacion del Jwt
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public JwtUtil(String jwtSecret, String jwtExpirationMs) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationMs = jwtExpirationMs;
    }
}
