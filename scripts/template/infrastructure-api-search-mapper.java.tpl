package dev.jgregorio.demo.back.infrastructure.api.${DOMAIN_LOWER}.search;

import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL};
import dev.jgregorio.demo.back.domain.${DOMAIN_LOWER}.${DOMAIN_CAPITAL}Search;
import api.in.infrastructure.dev.jgregorio.demo.back.GenericRequestApiMapper;
import api.in.infrastructure.dev.jgregorio.demo.back.GenericResponseApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ${DOMAIN_CAPITAL}SearchApiMapper
    extends GenericRequestApiMapper<${DOMAIN_CAPITAL}SearchRequest, ${DOMAIN_CAPITAL}Search>,
        GenericResponseApiMapper<${DOMAIN_CAPITAL}, ${DOMAIN_CAPITAL}SearchResponse> {}