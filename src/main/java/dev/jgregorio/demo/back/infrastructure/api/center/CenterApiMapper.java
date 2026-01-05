package dev.jgregorio.demo.back.infrastructure.api.center;

import dev.jgregorio.demo.back.domain.center.Center;
import dev.jgregorio.demo.back.infrastructure.api.GenericResponseApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CenterApiMapper extends GenericResponseApiMapper<Center, CenterResponse> {}
