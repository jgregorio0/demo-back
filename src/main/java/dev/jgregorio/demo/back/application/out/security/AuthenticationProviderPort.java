package dev.jgregorio.demo.back.application.out.security;

import dev.jgregorio.demo.back.domain.user.User;
import java.util.Optional;

public interface AuthenticationProviderPort {

  Optional<String> getAuthorizationHeaderValue();

  Optional<User> getAuthenticatedUser();
}
