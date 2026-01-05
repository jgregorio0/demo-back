package dev.jgregorio.demo.back.domain.${DOMAIN_LOWER};

import lombok.Builder;

@Builder(toBuilder = true)
public record ${DOMAIN_CAPITAL}(Long id) {
    // review domain attributes
}