package dev.jgregorio.demo.back.infrastructure.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.jgregorio.demo.back.domain.user.Authority;
import dev.jgregorio.demo.back.domain.user.User;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

  private final List<SimpleGrantedAuthority> authorities;
  private final User user;

  public JwtUser(User user) {
    this.user = user;
    this.authorities = extractAuthorities(user.authorities());
  }

  private List<SimpleGrantedAuthority> extractAuthorities(List<Authority> authorities) {
    if (authorities == null) {
      return Collections.emptyList();
    }
    return authorities.stream()
        .map(this::mapToSimpleGrantedAuthority)
        .filter(Objects::nonNull)
        .toList();
  }

  private SimpleGrantedAuthority mapToSimpleGrantedAuthority(Authority authority) {
    if (authority == null) {
      return null;
    }
    return new SimpleGrantedAuthority(authority.authority());
  }

  @Override
  public String getUsername() {
    return user.username();
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isEnabled() {
    return user.enabled();
  }

  @JsonIgnore
  public LocalDateTime getLastPasswordResetDate() {
    return user.lastPasswordResetDate();
  }

  public User getUser() {
    return user;
  }
}
