package com.example.ReconstruindoAtitudes.Infra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ReconstruindoAtitudes.Model.UsuarioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioModel usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("Reconst_Atitudes")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algoritmo);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro durante autenticação");
        }

    }

    public String validateToken(String token){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Reconst_Atitudes")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
