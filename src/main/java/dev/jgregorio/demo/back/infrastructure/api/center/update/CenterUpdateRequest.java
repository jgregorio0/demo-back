package dev.jgregorio.demo.back.infrastructure.api.center.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CenterUpdateRequest(
    @NotNull Long id,
    @NotNull Long clientId,
    @NotBlank String name,
    @NotBlank String address,
    @NotBlank String postalCode,
    @NotNull Long locationId) {}
