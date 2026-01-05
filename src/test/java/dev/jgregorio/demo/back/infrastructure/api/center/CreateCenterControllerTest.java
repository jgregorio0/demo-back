package dev.jgregorio.demo.back.infrastructure.api.center;

import static dev.jgregorio.demo.back.infrastructure.api.center.CenterControllerTestDataFactory.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jgregorio.demo.back.application.in.center.CenterUseCase;
import dev.jgregorio.demo.back.domain.center.Center;
import dev.jgregorio.demo.back.domain.center.CenterCreation;
import dev.jgregorio.demo.back.infrastructure.api.ApiPresentationTestHelper;
import dev.jgregorio.demo.back.infrastructure.api.center.create.CenterCreationApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.create.CenterCreationRequest;
import dev.jgregorio.demo.back.infrastructure.api.center.delete.CenterDeleteApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.read.CenterReadApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.search.CenterSearchApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.center.update.CenterUpdateApiMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CenterController.class)
@AutoConfigureMockMvc(addFilters = false)
class CreateCenterControllerTest extends ApiPresentationTestHelper {

  @MockitoBean private CenterUseCase centerService;
  @MockitoBean private CenterApiMapper mapper;
  @MockitoBean private CenterCreationApiMapper creationMapper;
  @MockitoBean private CenterReadApiMapper readMapper;
  @MockitoBean private CenterUpdateApiMapper updateMapper;
  @MockitoBean private CenterDeleteApiMapper deleteMapper;
  @MockitoBean private CenterSearchApiMapper searchMapper;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  @DisplayName("should create a new center when request is valid")
  void create_shouldCreateCenter_whenRequestIsValid() throws Exception {
    // Given
    CenterCreationRequest createRequest = createCenterCreationRequest();
    CenterCreation centerCreation = createCenterCreation();
    Center createdCenter = createCenter();
    CenterResponse centerResponse = createCenterResponse();

    when(creationMapper.fromRequest(any(CenterCreationRequest.class))).thenReturn(centerCreation);
    when(centerService.create(any(CenterCreation.class))).thenReturn(createdCenter);
    when(mapper.toResponse(createdCenter)).thenReturn(centerResponse);

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(createdCenter.id().intValue())))
        .andExpect(jsonPath("$.clientId", is(createdCenter.clientId().intValue())))
        .andExpect(jsonPath("$.name", is(createRequest.name())))
        .andExpect(jsonPath("$.address", is(createRequest.address())))
        .andExpect(jsonPath("$.postalCode", is(createRequest.postalCode())));

    verify(creationMapper).fromRequest(any(CenterCreationRequest.class));
    verify(centerService).create(any(CenterCreation.class));
    verify(mapper).toResponse(createdCenter);
  }

  @Test
  @DisplayName("should return 400 when clientId is null")
  void create_shouldReturnBadRequest_whenClientIdIsNull() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(null)
            .name(CENTER_NAME)
            .address(CENTER_ADDRESS)
            .postalCode(CENTER_POSTAL_CODE)
            .locationId(LOCATION_ID)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when name is null")
  void create_shouldReturnBadRequest_whenNameIsNull() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(CLIENT_ID)
            .name(null)
            .address(CENTER_ADDRESS)
            .postalCode(CENTER_POSTAL_CODE)
            .locationId(LOCATION_ID)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when name is blank")
  void create_shouldReturnBadRequest_whenNameIsBlank() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(CLIENT_ID)
            .name("   ")
            .address(CENTER_ADDRESS)
            .postalCode(CENTER_POSTAL_CODE)
            .locationId(LOCATION_ID)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when address is null")
  void create_shouldReturnBadRequest_whenAddressIsNull() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(CLIENT_ID)
            .name(CENTER_NAME)
            .address(null)
            .postalCode(CENTER_POSTAL_CODE)
            .locationId(LOCATION_ID)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when address is blank")
  void create_shouldReturnBadRequest_whenAddressIsBlank() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(CLIENT_ID)
            .name(CENTER_NAME)
            .address("")
            .postalCode(CENTER_POSTAL_CODE)
            .locationId(LOCATION_ID)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when postalCode is null")
  void create_shouldReturnBadRequest_whenPostalCodeIsNull() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(CLIENT_ID)
            .name(CENTER_NAME)
            .address(CENTER_ADDRESS)
            .postalCode(null)
            .locationId(LOCATION_ID)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when postalCode is blank")
  void create_shouldReturnBadRequest_whenPostalCodeIsBlank() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(CLIENT_ID)
            .name(CENTER_NAME)
            .address(CENTER_ADDRESS)
            .postalCode("  ")
            .locationId(LOCATION_ID)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when locationId is null")
  void create_shouldReturnBadRequest_whenLocationIdIsNull() throws Exception {
    // Given
    CenterCreationRequest invalidRequest =
        CenterCreationRequest.builder()
            .clientId(CLIENT_ID)
            .name(CENTER_NAME)
            .address(CENTER_ADDRESS)
            .postalCode(CENTER_POSTAL_CODE)
            .locationId(null)
            .build();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when request body is empty")
  void create_shouldReturnBadRequest_whenRequestBodyIsEmpty() throws Exception {
    // When & Then
    mockMvc
        .perform(post("/centers").contentType(MediaType.APPLICATION_JSON).content("{}"))
        .andExpect(status().isBadRequest());

    verify(centerService, never()).create(any(CenterCreation.class));
  }

  @Test
  @DisplayName("should return 400 when content type is not JSON")
  void create_shouldReturnBadRequest_whenContentTypeIsNotJson() throws Exception {
    // Given
    CenterCreationRequest createRequest = createCenterCreationRequest();

    // When & Then
    mockMvc
        .perform(
            post("/centers")
                .contentType(MediaType.TEXT_PLAIN)
                .content(objectMapper.writeValueAsString(createRequest)))
        .andExpect(status().isUnsupportedMediaType());

    verify(centerService, never()).create(any(CenterCreation.class));
  }
}
