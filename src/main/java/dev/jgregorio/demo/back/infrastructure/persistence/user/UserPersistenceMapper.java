package dev.jgregorio.demo.back.infrastructure.persistence.user;

import dev.jgregorio.demo.back.domain.user.User;
import dev.jgregorio.demo.back.infrastructure.persistence.GenericPersistenceMapper;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserPersistenceMapper extends GenericPersistenceMapper<User, UserEntity> {

  @Override
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "email", ignore = true)
  @Mapping(target = "authorities", ignore = true)
  User toDomain(UserEntity entity);

  @Override
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "modifiedBy", ignore = true)
  @Mapping(target = "modifiedDate", ignore = true)
  UserEntity toEntity(User domain);
}
