package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service para criar token.
 */
@Service
public class TokenService {

  /**
   * Método para criar o token.
   *
   * @param username recebendo Username.
   *
   * @return retornando token JWT.
   */
  public String generateToken(String username) {
    String secret = "secret";
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create()
        .withSubject(username)
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * Método auxiliar para tempo de expiração.
   *
   * @return retorna a data de expiração.
   */
  private Instant generateExpirationDate() {
    return Instant.now().plus(15, ChronoUnit.MINUTES);
  }

  /**
   * Método para verificar se o token é válido.
   *
   * @param token recebe o token.
   * @return o username.
   */
  public String decodeToken(String token) {
    String secret = "secret";
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }
}
