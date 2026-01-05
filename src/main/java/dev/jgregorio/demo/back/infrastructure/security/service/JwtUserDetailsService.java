package dev.jgregorio.demo.back.infrastructure.security.service;

import dev.jgregorio.demo.back.application.out.user.UserService;
import dev.jgregorio.demo.back.domain.user.User;
import dev.jgregorio.demo.back.infrastructure.security.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userService
            .getByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username));
    return new JwtUser(user);
  }
}
