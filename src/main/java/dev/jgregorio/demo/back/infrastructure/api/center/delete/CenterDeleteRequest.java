package dev.jgregorio.demo.back.infrastructure.api.center.delete;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CenterDeleteRequest(@NotNull Long id, @NotNull Long clientId) {}
