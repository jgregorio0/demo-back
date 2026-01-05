package dev.jgregorio.demo.back.domain.${DOMAIN_LOWER};

import lombok.Builder;

@Builder
public record ${DOMAIN_CAPITAL}Search(String term) {
    // review search attributes
}