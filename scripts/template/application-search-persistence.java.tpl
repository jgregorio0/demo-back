package dev.jgregorio.demo.back.application.${DOMAIN_LOWER};

import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL};
import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ${DOMAIN_CAPITAL}SearchPersistence {
  Page<${DOMAIN_CAPITAL}> search(${DOMAIN_CAPITAL}Search criteria, Pageable pageable);
}