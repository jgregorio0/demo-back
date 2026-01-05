package dev.jgregorio.demo.back.application.in.center;

import static dev.jgregorio.demo.back.application.in.center.CenterServiceTestDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.jgregorio.demo.back.application.out.center.CenterPersistencePort;
import dev.jgregorio.demo.back.domain.center.Center;
import dev.jgregorio.demo.back.domain.center.CenterCreation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCenterServiceTest {

  private static final Long NEXT_ID = 10L;

  @Mock private CenterPersistencePort persistence;
  @InjectMocks private CenterService service;

  private CenterCreation centerCreation;
  private Center center;

  @BeforeEach
  void setUp() {
    centerCreation = createCenterCreation();
    center = createCenter();
  }

  @Test
  @DisplayName("create should get next ID, convert creation to center, and persist it")
  void create_shouldGetNextIdAndPersistCenter() {
    // Given
    when(persistence.create(any(Center.class))).thenReturn(center);

    // When
    Center result = service.create(centerCreation);

    // Then
    verify(persistence)
        .create(
            argThat(
                c ->
                    c.clientId().equals(CLIENT_ID)
                        && c.name().equals(CENTER_NAME)
                        && c.address().equals(CENTER_ADDRESS)
                        && c.postalCode().equals(CENTER_POSTAL_CODE)));
    assertNotNull(result);
  }
}
