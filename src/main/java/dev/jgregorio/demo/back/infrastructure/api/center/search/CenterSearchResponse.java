package dev.jgregorio.demo.back.infrastructure.api.center.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.jgregorio.demo.back.domain.location.Location;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CenterSearchResponse(
    Long id, Long clientId, String name, String address, String postalCode, Location location) {}
