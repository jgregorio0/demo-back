package dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER}.search;

import dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface ${DOMAIN_CAPITAL}SearchEntityJpaRepository
    extends JpaRepository<${DOMAIN_CAPITAL}Entity, Long>, JpaSpecificationExecutor<${DOMAIN_CAPITAL}Entity> {

  @Override
  @NonNull
  Page<${DOMAIN_CAPITAL}Entity> findAll(
      @Nullable Specification<${DOMAIN_CAPITAL}Entity> spec, @NonNull Pageable pageable);
}