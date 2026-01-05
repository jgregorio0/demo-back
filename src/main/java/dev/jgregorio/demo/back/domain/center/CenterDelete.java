package dev.jgregorio.demo.back.domain.center;

import lombok.Builder;

@Builder(toBuilder = true)
public record CenterDelete(Long id, Long clientId) {}
