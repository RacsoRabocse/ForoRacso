package com.alura.cursos.forohub.racso.seguridad;

import com.alura.cursos.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ServicioFichas {
  @Value("${api.security.secret}")
  private String apiSecret;

  public String generToken(Usuario usuario) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      return JWT.create()
        .withIssuer("voll med")
        .withSubject(usuario.getEmail())
        .withClaim("id", usuario.getId())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
    } catch (JWTCreationException exception){
      throw  new RuntimeException();
    }
  }

  public String getSubject(String token) {
    DecodedJWT verifier = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      verifier = JWT.require(algorithm)
        .withIssuer("voll med")
        .build()
        .verify(token);
      verifier.getSubject();
    } catch (JWTVerificationException exception){
      System.out.println(exception.toString());
    }
    if (verifier.getSubject() == null) {
      throw new RuntimeException("Verifier invalido");
    }
    return verifier.getSubject();
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
  }
}
