package dev.jgregorio.demo.back.application.in.center;

import static dev.jgregorio.demo.back.application.in.center.CenterServiceTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.jgregorio.demo.back.application.out.center.CenterPersistencePort;
import dev.jgregorio.demo.back.domain.center.Center;
import dev.jgregorio.demo.back.domain.center.CenterRead;
import dev.jgregorio.demo.back.domain.exception.ResourceNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReadCenterServiceTest {

  @Mock private CenterPersistencePort persistence;
  @InjectMocks private CenterService service;

  private CenterRead centerRead;
  private Center center;

  @BeforeEach
  void setUp() {
    centerRead = createCenterRead();
    center = createCenter();
  }

  @Test
  @DisplayName("read should call persistence with correct id and clientId")
  void read_shouldCallPersistenceWithCorrectParameters() {
    // Given
    when(persistence.read(CENTER_ID, CLIENT_ID)).thenReturn(Optional.of(center));

    // When
    Center result = service.read(centerRead);

    // Then
    verify(persistence).read(CENTER_ID, CLIENT_ID);
    assertNotNull(result);
  }

  @Test
  @DisplayName("read should throw ResourceNotFoundException when center not found")
  void read_shouldThrowResourceNotFoundException_whenCenterNotFound() {
    // Given
    when(persistence.read(CENTER_ID, CLIENT_ID)).thenReturn(Optional.empty());

    // When & Then
    assertThrows(ResourceNotFoundException.class, () -> service.read(centerRead));
    verify(persistence).read(CENTER_ID, CLIENT_ID);
  }
}
