package com.str.shootingresulttracker.infrastructure.user;

import com.str.shootingresulttracker.domain.user.User;
import org.mapstruct.Mapper;

@Mapper
interface UserMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);

}
