package dev.jgregorio.demo.back.application.in.center;

import static dev.jgregorio.demo.back.application.in.center.CenterServiceTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.jgregorio.demo.back.application.out.center.CenterPersistencePort;
import dev.jgregorio.demo.back.domain.center.Center;
import dev.jgregorio.demo.back.domain.center.CenterUpdate;
import dev.jgregorio.demo.back.domain.exception.ResourceNotFoundException;
import dev.jgregorio.demo.back.domain.location.Location;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCenterServiceTest {

  @Mock private CenterPersistencePort persistence;
  @InjectMocks private CenterService service;

  private CenterUpdate centerUpdate;

  @BeforeEach
  void setUp() {
    centerUpdate = createCenterUpdate();
  }

  @Test
  @DisplayName("update should read existing center, apply updates, and persist")
  void update_shouldReadCenterApplyUpdatesAndPersist() {
    // Given
    Center existingCenter =
        Center.builder()
            .id(CENTER_ID)
            .clientId(CLIENT_ID)
            .name("Old Name")
            .address("Old Address")
            .postalCode("00000")
            .location(Location.from(LOCATION_ID))
            .build();

    Center updatedCenter =
        Center.builder()
            .id(CENTER_ID)
            .clientId(CLIENT_ID)
            .name("Updated Name")
            .address("Updated Address")
            .postalCode("54321")
            .location(Location.from(LOCATION_ID))
            .build();

    when(persistence.read(CENTER_ID, CLIENT_ID)).thenReturn(Optional.of(existingCenter));
    when(persistence.update(any(Center.class))).thenReturn(updatedCenter);

    // When
    Center result = service.update(centerUpdate);

    // Then
    verify(persistence).read(CENTER_ID, CLIENT_ID);
    verify(persistence)
        .update(
            argThat(
                c ->
                    c.id().equals(CENTER_ID)
                        && c.clientId().equals(CLIENT_ID)
                        && c.name().equals("Updated Name")
                        && c.address().equals("Updated Address")
                        && c.postalCode().equals("54321")));
    assertNotNull(result);
  }

  @Test
  @DisplayName("update should throw ResourceNotFoundException when center not found")
  void update_shouldThrowResourceNotFoundException_whenCenterNotFound() {
    // Given
    when(persistence.read(CENTER_ID, CLIENT_ID)).thenReturn(Optional.empty());

    // When & Then
    assertThrows(ResourceNotFoundException.class, () -> service.update(centerUpdate));
    verify(persistence).read(CENTER_ID, CLIENT_ID);
    verify(persistence, never()).update(any(Center.class));
  }
}
