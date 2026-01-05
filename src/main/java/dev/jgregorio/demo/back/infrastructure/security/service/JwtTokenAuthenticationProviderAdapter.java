package dev.jgregorio.demo.back.infrastructure.security.service;

import static dev.jgregorio.demo.back.infrastructure.security.JwtTokenUtil.*;

import dev.jgregorio.demo.back.application.out.security.AuthenticationProviderPort;
import dev.jgregorio.demo.back.domain.user.User;
import dev.jgregorio.demo.back.infrastructure.security.JwtUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
public class JwtTokenAuthenticationProviderAdapter implements AuthenticationProviderPort {

  static final String HEADER_PARAM_TYPE_KEY = "typ";
  static final String HEADER_PARAM_TYPE_VALUE_JWT = "JWT";

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  @Value("${jwt.header}")
  private String tokenHeader;

  @Value("${jwt.prefijo}")
  private String tokenHeaderPrefijo;

  public Claims parseToken(String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
      JwtParser parser = Jwts.parser().verifyWith(key).build();
      return (Claims) parser.parse(token).getPayload();
    } catch (Exception e) {
      log.debug(e.getMessage(), e);
      return Jwts.claims().build();
    }
  }

  public Claims parseTokenUnsecured(String token) {
    try {
      String[] chunks = token.split("\\.");
      byte[] payload = Base64.getDecoder().decode(chunks[1]);
      String unsignedToken =
          Jwts.builder()
              .header()
              .add(Header.ALGORITHM, Jwts.SIG.NONE.getId())
              .and()
              .content(payload)
              .compact();
      return Jwts.parser().unsecured().build().parseUnsecuredClaims(unsignedToken).getPayload();
    } catch (Exception e) {
      log.debug(e.getMessage(), e);
      return Jwts.claims().empty().build();
    }
  }

  public Claims getClaimsFromTokenIgnoreExpiration(String token) {
    Claims claims;
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
      JwtParser parser = Jwts.parser().verifyWith(key).build();
      claims = (Claims) parser.parse(token).getPayload();
    } catch (ExpiredJwtException e) {
      log.debug(e.getMessage(), e);
      return e.getClaims();
    } catch (Exception e) {
      log.debug(e.getMessage(), e);
      claims = null;
    }
    return claims;
  }

  public Boolean validateToken(Claims claims, UserDetails userDetails) {
    JwtUser user = (JwtUser) userDetails;
    final String username = getUsername(claims);
    final LocalDateTime created = getCreatedDate(claims);
    Instant exp = getExpiration(claims);
    return isEqualUsername(username, user)
        && !isExpired(exp)
        && isCreatedAfterLastPasswordReset(user, created);
  }

  private boolean isCreatedAfterLastPasswordReset(JwtUser user, LocalDateTime created) {
    return user.getLastPasswordResetDate() != null
        && created.isAfter(user.getLastPasswordResetDate());
  }

  private boolean isExpired(Instant exp) {
    return exp.isBefore(Instant.now());
  }

  private boolean isEqualUsername(String username, JwtUser user) {
    return username != null && username.equals(user.getUsername());
  }

  public String getTokenFromHeader(HttpServletRequest request) {
    String authToken = request.getHeader(this.tokenHeader);
    if (authToken != null && authToken.startsWith(this.tokenHeaderPrefijo)) {
      authToken = authToken.substring(this.tokenHeaderPrefijo.length()).trim();
    }
    return authToken;
  }

  public String generateToken(Map<String, Object> claims) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    return Jwts.builder()
        .header()
        .add(HEADER_PARAM_TYPE_KEY, HEADER_PARAM_TYPE_VALUE_JWT)
        .and()
        .claims()
        .empty()
        .add(claims)
        .and()
        .expiration(generateExpirationDate())
        .signWith(key)
        .compact();
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.expiration * 1000);
  }

  public Optional<String> getAuthorizationHeaderValue() {
    return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
        .map(ServletRequestAttributes.class::cast)
        .map(ServletRequestAttributes::getRequest)
        .map(request -> request.getHeader(HttpHeaders.AUTHORIZATION));
  }

  @Override
  public Optional<User> getAuthenticatedUser() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(a -> (JwtUser) a.getPrincipal())
        .map(JwtUser::getUser);
  }
}
