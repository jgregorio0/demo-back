package dev.jgregorio.demo.back.infrastructure.security.filter;

import dev.jgregorio.demo.back.infrastructure.security.service.JwtTokenAuthenticationProviderAdapter;
import dev.jgregorio.demo.back.infrastructure.security.service.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("development")
public class DevJwtAuthenticationTokenFilter extends JwtAuthenticationTokenFilter {

  public DevJwtAuthenticationTokenFilter(
      JwtUserDetailsService jwtUserDetailsService,
      JwtTokenAuthenticationProviderAdapter jwtTokenService) {
    super(jwtUserDetailsService, jwtTokenService);
  }

  @Override
  protected Claims getClaimsFromToken(String token) {
    return getJwtTokenService().getClaimsFromTokenIgnoreExpiration(token);
  }

  @Override
  protected boolean isValidToken(Claims claims, UserDetails userDetails) {
    return true;
  }
}
