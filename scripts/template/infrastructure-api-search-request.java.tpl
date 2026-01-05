package dev.jgregorio.demo.back.infrastructure.api.${DOMAIN_LOWER}.search;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ${DOMAIN_CAPITAL}SearchRequest(@NotBlank String term) {
    // review request parameters
}