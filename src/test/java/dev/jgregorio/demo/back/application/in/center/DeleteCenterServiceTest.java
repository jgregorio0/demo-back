package dev.jgregorio.demo.back.application.in.center;

import static dev.jgregorio.demo.back.application.in.center.CenterServiceTestDataFactory.*;
import static org.mockito.Mockito.*;

import dev.jgregorio.demo.back.application.out.center.CenterPersistencePort;
import dev.jgregorio.demo.back.domain.center.CenterDelete;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteCenterServiceTest {

  @Mock private CenterPersistencePort persistence;
  @InjectMocks private CenterService service;

  @Test
  @DisplayName("delete should call persistence with correct id and clientId")
  void delete_shouldCallPersistenceWithCorrectParameters() {
    // Given
    CenterDelete centerDelete = createCenterDelete();
    doNothing().when(persistence).delete(CENTER_ID, CLIENT_ID);

    // When
    service.delete(centerDelete);

    // Then
    verify(persistence).delete(CENTER_ID, CLIENT_ID);
  }
}
