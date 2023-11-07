package com.str.shootingresulttracker.domain.magazine;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
interface WeaponMapper {

    List<WeaponDto> toDto(List<Weapon> weapons);

}
