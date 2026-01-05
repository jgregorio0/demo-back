package dev.jgregorio.demo.back.infrastructure.api.center.search;

import lombok.Builder;

@Builder
public record CenterSearchRequest(String name, String address, String postalCode) {}
