package com.str.shootingresulttracker.usermanagment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
    UserDto toDto(UserEntity entity);
}
