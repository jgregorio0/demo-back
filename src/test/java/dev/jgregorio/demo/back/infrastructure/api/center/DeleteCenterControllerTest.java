package dev.jgregorio.demo.back.infrastructure.api.center;

import static dev.jgregorio.demo.back.infrastructure.api.center.CenterControllerTestDataFactory.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jgregorio.demo.back.application.in.center.CenterUseCase;
import dev.jgregorio.demo.back.domain.center.CenterDelete;
import dev.jgregorio.demo.back.infrastructure.api.ApiPresentationTestHelper;
import dev.jgregorio.demo.back.infrastructure.api.center.create.CenterCreationApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.delete.CenterDeleteApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.delete.CenterDeleteRequest;
import dev.jgregorio.demo.back.infrastructure.api.center.read.CenterReadApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.search.CenterSearchApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.update.CenterUpdateApiMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CenterController.class)
@AutoConfigureMockMvc(addFilters = false)
class DeleteCenterControllerTest extends ApiPresentationTestHelper {

  @MockitoBean private CenterUseCase centerService;
  @MockitoBean private CenterSearchApiMapper searchMapper;
  @MockitoBean private CenterApiMapper mapper;
  @MockitoBean private CenterCreationApiMapper creationMapper;
  @MockitoBean private CenterReadApiMapper readMapper;
  @MockitoBean private CenterUpdateApiMapper updateMapper;
  @MockitoBean private CenterDeleteApiMapper deleteMapper;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  @DisplayName("should delete a center when id and clientId are valid")
  void delete_shouldDeleteCenter_whenIdAndClientIdAreValid() throws Exception {
    // Given
    CenterDeleteRequest deleteRequest = createCenterDeleteRequest();
    CenterDelete centerDelete = createCenterDelete();

    when(deleteMapper.fromRequest(any(CenterDeleteRequest.class))).thenReturn(centerDelete);
    doNothing().when(centerService).delete(centerDelete);

    // When & Then
    mockMvc
        .perform(
            delete("/centers")
                .param("id", String.valueOf(deleteRequest.id()))
                .param("clientId", String.valueOf(deleteRequest.clientId())))
        .andExpect(status().isNoContent());

    verify(deleteMapper).fromRequest(any(CenterDeleteRequest.class));
    verify(centerService).delete(centerDelete);
  }

  @Test
  @DisplayName("should return 400 when id is null")
  void delete_shouldReturnBadRequest_whenIdIsNull() throws Exception {
    // When & Then
    mockMvc
        .perform(delete("/centers").param("clientId", String.valueOf(CLIENT_ID)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).delete(any(CenterDelete.class));
  }

  @Test
  @DisplayName("should return 400 when clientId is null")
  void delete_shouldReturnBadRequest_whenClientIdIsNull() throws Exception {
    // When & Then
    mockMvc
        .perform(delete("/centers").param("id", String.valueOf(CENTER_ID)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).delete(any(CenterDelete.class));
  }

  @Test
  @DisplayName("should return 400 when id is not a valid number")
  void delete_shouldReturnBadRequest_whenIdIsNotANumber() throws Exception {
    // When & Then
    mockMvc
        .perform(
            delete("/centers").param("id", "invalid").param("clientId", String.valueOf(CLIENT_ID)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).delete(any(CenterDelete.class));
  }

  @Test
  @DisplayName("should return 400 when clientId is not a valid number")
  void delete_shouldReturnBadRequest_whenClientIdIsNotANumber() throws Exception {
    // When & Then
    mockMvc
        .perform(
            delete("/centers").param("id", String.valueOf(CENTER_ID)).param("clientId", "invalid"))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).delete(any(CenterDelete.class));
  }
}
