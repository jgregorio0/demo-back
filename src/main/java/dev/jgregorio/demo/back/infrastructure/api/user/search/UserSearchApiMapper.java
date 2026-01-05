package dev.jgregorio.demo.back.infrastructure.api.user.search;

import dev.jgregorio.demo.back.domain.user.User;
import dev.jgregorio.demo.back.domain.user.UserSearch;
import dev.jgregorio.demo.back.infrastructure.api.GenericRequestApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.GenericResponseApiMapper;
import dev.jgregorio.demo.back.infrastructure.api.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserSearchApiMapper
    extends GenericResponseApiMapper<User, UserResponse>,
        GenericRequestApiMapper<UserSearchRequest, UserSearch> {}
