package dev.jgregorio.demo.back.infrastructure.security.filter;

import dev.jgregorio.demo.back.infrastructure.security.JwtTokenUtil;
import dev.jgregorio.demo.back.infrastructure.security.service.JwtTokenAuthenticationProviderAdapter;
import dev.jgregorio.demo.back.infrastructure.security.service.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter implements JwtTokenFilter {

  private final JwtUserDetailsService jwtUserDetailsService;
  private final JwtTokenAuthenticationProviderAdapter jwtTokenService;

  private static void authenticateUser(HttpServletRequest request, UserDetails userDetails) {
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private boolean isAuthenticateUserRequired(String username) {
    return username != null && SecurityContextHolder.getContext().getAuthentication() == null;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    final String authToken = jwtTokenService.getTokenFromHeader(request);
    if (authToken == null) {
      chain.doFilter(request, response);
      return;
    }
    Claims claims = getClaimsFromToken(authToken);
    String username = JwtTokenUtil.getUsername(claims);
    log.debug("Checking authentication for user: {}", username);
    if (isAuthenticateUserRequired(username)) {
      UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
      if (isValidToken(claims, userDetails)) {
        authenticateUser(request, userDetails);
        log.info("User '{}' authenticated, setting security context", username);
      } else {
        log.warn("Invalid token for user: {}", username);
      }
    }
    chain.doFilter(request, response);
  }

  protected Claims getClaimsFromToken(String token) {
    return jwtTokenService.parseToken(token);
  }

  protected boolean isValidToken(Claims claims, UserDetails userDetails) {
    return Boolean.TRUE.equals(jwtTokenService.validateToken(claims, userDetails));
  }

  protected JwtTokenAuthenticationProviderAdapter getJwtTokenService() {
    return jwtTokenService;
  }
}
