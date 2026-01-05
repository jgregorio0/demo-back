package dev.jgregorio.demo.back.domain.center;

import dev.jgregorio.demo.back.domain.location.Location;
import lombok.Builder;

@Builder(toBuilder = true)
public record CenterCreation(
    Long clientId, String name, String address, String postalCode, Long locationId) {

  public Center toCenter() {
    return Center.builder()
        .clientId(clientId)
        .name(name)
        .address(address)
        .postalCode(postalCode)
        .location(Location.from(locationId))
        .build();
  }
}
