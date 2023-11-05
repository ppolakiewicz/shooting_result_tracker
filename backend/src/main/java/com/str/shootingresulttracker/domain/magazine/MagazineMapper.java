package com.str.shootingresulttracker.domain.magazine;

import org.mapstruct.Mapper;

@Mapper
interface MagazineMapper {

    MagazineDto toDto(Magazine magazine);

}
