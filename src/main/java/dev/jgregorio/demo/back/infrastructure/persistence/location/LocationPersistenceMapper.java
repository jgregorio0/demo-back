package dev.jgregorio.demo.back.infrastructure.persistence.location;

import dev.jgregorio.demo.back.domain.location.Location;
import dev.jgregorio.demo.back.infrastructure.persistence.GenericPersistenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationPersistenceMapper
    extends GenericPersistenceMapper<Location, LocationEntity> {

  @Override
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "modifiedBy", ignore = true)
  @Mapping(target = "modifiedDate", ignore = true)
  LocationEntity toEntity(Location domain);
}
