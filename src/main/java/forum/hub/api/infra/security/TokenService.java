package forum.hub.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import forum.hub.api.domain.user.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        System.out.println("Generating token");
        // Implementation for generating a token
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API Forum.hub")
                .withSubject(user.getLogin())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generating token", exception);
        }
    }

    public String getSubject(String JWTToken){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("API Forum.hub")
                .build()
                .verify(JWTToken)
                .getSubject();
                
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error verifying token", exception);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
