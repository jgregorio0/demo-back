package dev.jgregorio.demo.back.infrastructure.persistence.audit;

import dev.jgregorio.demo.back.application.out.security.AuthenticationProviderPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditorConfig {

  @Bean
  public AuditorAware<Long> auditorProvider(
      final AuthenticationProviderPort authenticationProvider) {
    return AuthenticationAuditorAware.builder()
        .authenticationProvider(authenticationProvider)
        .build();
  }
}
