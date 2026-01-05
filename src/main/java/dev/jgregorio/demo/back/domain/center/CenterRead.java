package dev.jgregorio.demo.back.domain.center;

import lombok.Builder;

@Builder(toBuilder = true)
public record CenterRead(Long id, Long clientId) {}
