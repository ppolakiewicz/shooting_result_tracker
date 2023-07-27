package com.str.shootingresulttracker.usermanagment;

import org.mapstruct.Mapper;

@Mapper
interface UserMapper {

    UserDto toDto(UserEntity entity);
}
