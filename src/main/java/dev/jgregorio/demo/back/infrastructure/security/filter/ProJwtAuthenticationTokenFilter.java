package dev.jgregorio.demo.back.infrastructure.security.filter;

import dev.jgregorio.demo.back.infrastructure.security.service.JwtTokenAuthenticationProviderAdapter;
import dev.jgregorio.demo.back.infrastructure.security.service.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!development")
public class ProJwtAuthenticationTokenFilter extends JwtAuthenticationTokenFilter {

  public ProJwtAuthenticationTokenFilter(
      JwtUserDetailsService jwtUserDetailsService,
      JwtTokenAuthenticationProviderAdapter jwtTokenService) {
    super(jwtUserDetailsService, jwtTokenService);
  }
}
