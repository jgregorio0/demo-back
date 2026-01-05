package dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER}.search;

import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL};
import persistence.out.infrastructure.dev.jgregorio.demo.back.GenericPersistenceMapper;
import dev.jgregorio.demo.back.infrastructure.persistence.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Entity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ${DOMAIN_CAPITAL}SearchPersistenceMapper
    extends GenericPersistenceMapper<${DOMAIN_CAPITAL}, ${DOMAIN_CAPITAL}Entity> {}