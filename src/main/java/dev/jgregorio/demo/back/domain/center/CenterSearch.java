package dev.jgregorio.demo.back.domain.center;

import lombok.Builder;

@Builder(toBuilder = true)
public record CenterSearch(String name, String address, String postalCode) {}
