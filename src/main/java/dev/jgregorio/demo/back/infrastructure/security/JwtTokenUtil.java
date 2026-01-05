package dev.jgregorio.demo.back.infrastructure.security;

import io.jsonwebtoken.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.lang.NonNull;

public class JwtTokenUtil implements Serializable {

  static final String CLAIM_KEY_CREATED = "created";

  private JwtTokenUtil() {
    throw new IllegalStateException("Class is not instantiable.");
  }

  public static String getUsername(@NonNull Claims claims) {
    return claims.getSubject();
  }

  public static LocalDateTime getCreatedDate(@NonNull Claims claims) {
    return Instant.ofEpochMilli(claims.get(CLAIM_KEY_CREATED, Long.class))
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }

  public static Instant getExpiration(Claims claims) {
    return claims.getExpiration().toInstant();
  }
}
