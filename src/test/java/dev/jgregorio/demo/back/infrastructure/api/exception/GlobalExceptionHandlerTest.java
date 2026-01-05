package dev.jgregorio.demo.back.infrastructure.api.exception;

import static org.assertj.core.api.Assertions.assertThat;

import dev.jgregorio.demo.back.domain.exception.ResourceNotFoundException;
import java.net.URI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  @DisplayName("handleResourceNotFoundException should return 404 ProblemDetail")
  void handleResourceNotFoundException_shouldReturn404() {
    // Given
    String errorMessage = "Center not found";
    ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

    // When
    ProblemDetail result = handler.handleResourceNotFoundException(exception);

    // Then
    assertThat(result.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(result.getDetail()).isEqualTo(errorMessage);
    assertThat(result.getTitle()).isEqualTo("Resource Not Found");
    assertThat(result.getType())
        .isEqualTo(URI.create("https://example.com/probs/resource-not-found"));
  }

  @Test
  @DisplayName("handleException should return 500 ProblemDetail")
  void handleException_shouldReturn500() {
    // Given
    String errorMessage = "Unexpected error";
    Exception exception = new RuntimeException(errorMessage);

    // When
    ProblemDetail result = handler.handleException(exception);

    // Then
    assertThat(result.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    assertThat(result.getDetail()).isEqualTo("An unexpected error occurred");
    assertThat(result.getTitle()).isEqualTo("Internal Server Error");
    assertThat(result.getType())
        .isEqualTo(URI.create("https://example.com/probs/internal-server-error"));
  }
}
