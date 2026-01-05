package dev.jgregorio.demo.back.infrastructure.api.center;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.jgregorio.demo.back.OracleContainerIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SearchCenterControllerIT extends OracleContainerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("GET /centers/search should return 200 OK and center is found by name")
  @Sql({"/dev/jgregorio/demo/back/infrastructure/persistence/center/insert_center_1_client_2.sql"})
  void search_shouldReturn200OkAndCenter_whenFoundFilteringByName() throws Exception {
    // Given
    // Center with name "Center 1"
    // When
    mockMvc
        .perform(get("/centers/search").param("name", "center 1"))
        // Then
        .andExpect(status().isOk())
        .andExpect(
            mvcResult -> {
              String jsonResponse = mvcResult.getResponse().getContentAsString();
              assertThat(jsonResponse).contains("\"name\":\"Center 1\"");
            });
  }
}
