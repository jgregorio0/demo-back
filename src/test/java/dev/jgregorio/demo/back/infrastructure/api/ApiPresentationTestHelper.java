package dev.jgregorio.demo.back.infrastructure.api;

import dev.jgregorio.demo.back.infrastructure.security.filter.ProJwtAuthenticationTokenFilter;
import dev.jgregorio.demo.back.infrastructure.security.service.JwtTokenAuthenticationProviderAdapter;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

public class ApiPresentationTestHelper {

  @MockitoBean private JwtTokenAuthenticationProviderAdapter tokenService;
  @MockitoBean private ProJwtAuthenticationTokenFilter tokenFilter;
}
