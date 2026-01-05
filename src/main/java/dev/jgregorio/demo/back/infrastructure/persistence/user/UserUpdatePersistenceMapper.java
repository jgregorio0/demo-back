package dev.jgregorio.demo.back.infrastructure.persistence.user;

import dev.jgregorio.demo.back.domain.user.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserUpdatePersistenceMapper {

  // Mappstruct cannot use constructor injection.
  @Autowired protected UserJpaPersistenceRepository userEntityJpaRepository;
  @Autowired protected UserPersistenceMapper userMapper;

  public User toDomain(UserEntity entity) {
    return userMapper.toDomain(entity);
  }

  public UserEntity toEntity(User domain) {
    if (domain == null || domain.id() == null) {
      return null;
    }
    return userEntityJpaRepository.getReferenceById(domain.id());
  }
}
