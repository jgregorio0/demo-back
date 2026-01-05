package dev.jgregorio.demo.back.infrastructure.api.center.update;

import dev.jgregorio.demo.back.domain.center.CenterUpdate;
import dev.jgregorio.demo.back.infrastructure.api.GenericRequestApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CenterUpdateApiMapper
    extends GenericRequestApiMapper<CenterUpdateRequest, CenterUpdate> {}
