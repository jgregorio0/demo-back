package dev.jgregorio.demo.back.infrastructure.api.center.read;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CenterReadRequest(@NotNull Long id, @NotNull Long clientId) {}
