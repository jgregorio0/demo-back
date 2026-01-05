package dev.jgregorio.demo.back.infrastructure.security;

import dev.jgregorio.demo.back.domain.user.Authority;
import dev.jgregorio.demo.back.domain.user.User;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class TokenTestUtil {

  public static User buildExeviUser(String username) {
    return User.builder()
        .username(username)
        .email("exevi_user@preving.com")
        .name("Exevi")
        .surname("User Uno")
        .id(151238L)
        .authorities(
            Arrays.asList(
                Authority.builder().authority("17-40300").build(),
                Authority.builder().authority("17-40100").build(),
                Authority.builder().authority("17-40200").build(),
                Authority.builder().authority("17-40400").build(),
                Authority.builder().authority("17-40600").build(),
                Authority.builder().authority("17-40700").build(),
                Authority.builder().authority("17-40702").build()))
        .lastPasswordResetDate(null)
        .enabled(true)
        .build();
  }

  public static Map<String, Object> buildExeviUserClaims(String username, Long expiration) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("sub", username);
    claims.put("u_id", 151238);
    claims.put("u_email", "exevi_user@preving.com");
    claims.put("created", System.currentTimeMillis());
    claims.put("u_apellidos", "User Uno ");
    claims.put(
        "roles",
        Arrays.asList(
            "17-40300", "17-40100", "17-40200", "17-40400", "17-40600", "17-40700", "17-40702"));
    claims.put("u_restricciones_por_empresas", java.util.Collections.emptyList());
    claims.put("exp", expiration); // Example expiration
    claims.put("u_nombre", "Exevi");
    return claims;
  }
}
