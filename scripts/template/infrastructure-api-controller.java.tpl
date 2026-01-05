package dev.jgregorio.demo.back.infrastructure.api.${DOMAIN_LOWER};

import dev.jgregorio.demo.back.application.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Service;
import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL};
import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Search;
import dev.jgregorio.demo.back.infrastructure.api.${DOMAIN_LOWER}.search.${DOMAIN_CAPITAL}SearchApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.${DOMAIN_LOWER}.search.${DOMAIN_CAPITAL}SearchRequest;
import dev.jgregorio.demo.back.infrastructure.api.${DOMAIN_LOWER}.search.${DOMAIN_CAPITAL}SearchResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${DOMAIN_LOWER}s")
@RequiredArgsConstructor
public class ${DOMAIN_CAPITAL}Controller {

  private final ${DOMAIN_CAPITAL}Service ${DOMAIN_LOWER}Service;
  private final ${DOMAIN_CAPITAL}SearchApiMapper ${DOMAIN_LOWER}SearchApiMapper;

  @PostMapping("/search")
  public ResponseEntity<Page<${DOMAIN_CAPITAL}SearchResponse>> search(
      @RequestBody @Valid final ${DOMAIN_CAPITAL}SearchRequest searchRequest,
      @PageableDefault(size = 100) final Pageable pageable) {
    ${DOMAIN_CAPITAL}Search criteria = ${DOMAIN_LOWER}SearchApiMapper.fromRequest(searchRequest);
    Page<${DOMAIN_CAPITAL}> ${DOMAIN_LOWER}Page = ${DOMAIN_LOWER}Service.search(criteria, pageable);
    Page<${DOMAIN_CAPITAL}SearchResponse> ${DOMAIN_LOWER}Responsepage =
        ${DOMAIN_LOWER}Page.map(${DOMAIN_LOWER}SearchApiMapper::toResponse);
    return ResponseEntity.ok(${DOMAIN_LOWER}Responsepage);
  }
}