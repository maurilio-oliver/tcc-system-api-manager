package br.unip.tcc.tccapi.controller;


import br.unip.tcc.tccapi.model.Member;
import br.unip.tcc.tccapi.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{br.unip.tcc.tccapi.service.auth.secretKey}")
    private String secreat;

    public String generateToken(User member) {

        return JWT.create()
                .withSubject(member.getUsername()).withIssuer("API auth")
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00"))).withSubject(member.getUsername())
                .sign(Algorithm.HMAC256(secreat));
    }

    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secreat);
        try {
            return JWT.require(algorithm)
                    .withIssuer("API auth")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }

    }
}
