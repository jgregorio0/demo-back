package dev.jgregorio.demo.back.infrastructure.persistence.audit;

import dev.jgregorio.demo.back.application.out.security.AuthenticationProviderPort;
import dev.jgregorio.demo.back.domain.user.User;
import java.util.Optional;
import lombok.Builder;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;

@Builder
public record AuthenticationAuditorAware(AuthenticationProviderPort authenticationProvider)
    implements AuditorAware<Long> {

  @Override
  @NonNull
  public Optional<Long> getCurrentAuditor() {
    return authenticationProvider.getAuthenticatedUser().map(User::id);
  }
}
