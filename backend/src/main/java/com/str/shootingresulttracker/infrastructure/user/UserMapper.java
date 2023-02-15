package com.str.shootingresulttracker.infrastructure.user;

import org.mapstruct.Mapper;
import srt.user.User;

@Mapper
interface UserMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);

}
