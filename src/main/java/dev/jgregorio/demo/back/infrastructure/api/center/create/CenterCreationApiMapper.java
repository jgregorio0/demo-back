package dev.jgregorio.demo.back.infrastructure.api.center.create;

import dev.jgregorio.demo.back.domain.center.CenterCreation;
import dev.jgregorio.demo.back.infrastructure.api.GenericRequestApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CenterCreationApiMapper
    extends GenericRequestApiMapper<CenterCreationRequest, CenterCreation> {}
