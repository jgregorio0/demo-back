package dev.jgregorio.demo.back.infrastructure.api.user.search;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserSearchRequest(@NotBlank String fullName) {}
