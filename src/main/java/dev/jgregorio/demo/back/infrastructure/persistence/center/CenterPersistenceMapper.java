package dev.jgregorio.demo.back.infrastructure.persistence.center;

import dev.jgregorio.demo.back.domain.center.Center;
import dev.jgregorio.demo.back.infrastructure.persistence.GenericPersistenceMapper;
import dev.jgregorio.demo.back.infrastructure.persistence.location.LocationPersistenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {LocationPersistenceMapper.class})
public interface CenterPersistenceMapper extends GenericPersistenceMapper<Center, CenterEntity> {

  @Override
  @Mapping(target = "id", source = "id.id")
  @Mapping(target = "clientId", source = "id.clientId")
  Center toDomain(CenterEntity entity);

  @Override
  @Mapping(target = "id.id", source = "id")
  @Mapping(target = "id.clientId", source = "clientId")
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "modifiedBy", ignore = true)
  @Mapping(target = "modifiedDate", ignore = true)
  CenterEntity toEntity(Center domain);
}
