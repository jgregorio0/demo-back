package dev.jgregorio.demo.back.domain.user;

import lombok.Builder;

@Builder(toBuilder = true)
public record Authority(String authority) {}
