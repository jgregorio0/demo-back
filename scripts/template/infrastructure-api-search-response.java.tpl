package dev.jgregorio.demo.back.infrastructure.api.${DOMAIN_LOWER}.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.jgregorio.demo.back.infrastructure.location.LocationResponse;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ${DOMAIN_CAPITAL}SearchResponse(Long id) {
    // review response attributes
}