package dev.jgregorio.demo.back.infrastructure.security;

import static org.assertj.core.api.Assertions.assertThat;

import dev.jgregorio.demo.back.infrastructure.security.service.JwtTokenAuthenticationProviderAdapter;
import io.jsonwebtoken.Claims;
import java.util.Map;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.jdbc.Sql;

@Tag("excluded-pitest")
@SpringBootTest
class ValidationTokenIntegrationTest {

  @Autowired JwtTokenAuthenticationProviderAdapter jwtTokenService;

  @Autowired UserDetailsService userDetailsService;

  @Test
  @Sql("/dev/jgregorio/demo/back/infrastructure/security/insert_test_user.sql")
  void validateToken_shouldReturnTrue_whenGeneratedTokenForUser() {
    // Given
    long expiration = System.currentTimeMillis() / 1000 + 999999999L;
    Map<String, Object> claims = TokenTestUtil.buildExeviUserClaims("test_user", expiration);
    String token = jwtTokenService.generateToken(claims);
    Claims parsedClaims = jwtTokenService.parseToken(token);
    String username = JwtTokenUtil.getUsername(parsedClaims);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    Claims claimsFromToken = jwtTokenService.parseToken(token);
    // When
    Boolean valid = jwtTokenService.validateToken(claimsFromToken, userDetails);
    // Then
    assertThat(valid).isTrue();
  }
}
