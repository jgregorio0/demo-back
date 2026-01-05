package dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER}.search;

import dev.jgregorio.demo.back.application.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}SearchPersistence;
import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL};
import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Search;
import dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Entity;
import dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ${DOMAIN_CAPITAL}SearchJpaPersistence implements ${DOMAIN_CAPITAL}SearchPersistence {

  private final ${DOMAIN_CAPITAL}SearchPersistenceMapper mapper;
  private final ${DOMAIN_CAPITAL}SearchEntityJpaRepository jpaRepository;

  @Override
  public Page<${DOMAIN_CAPITAL}> search(${DOMAIN_CAPITAL}Search criteria, Pageable pageable) {
    Specification<${DOMAIN_CAPITAL}Entity> spec = ${DOMAIN_CAPITAL}Specification.search(criteria);
    Page<${DOMAIN_CAPITAL}Entity> entitiesPage = jpaRepository.findAll(spec, pageable);
    return entitiesPage.map(mapper::toDomain);
  }
}