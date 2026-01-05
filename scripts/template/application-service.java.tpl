package dev.jgregorio.demo.back.application.${DOMAIN_LOWER};

import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL};
import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Search;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ${DOMAIN_CAPITAL}Service {

  private final ${DOMAIN_CAPITAL}SearchPersistence ${DOMAIN_LOWER}SearchPersistence;

  @Transactional(readOnly = true)
  public Page<${DOMAIN_CAPITAL}> search(final ${DOMAIN_CAPITAL}Search criteria, final Pageable pageable) {
    return ${DOMAIN_LOWER}SearchPersistence.search(criteria, pageable);
  }
}