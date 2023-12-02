package com.str.shootingresulttracker.domain.training;

import org.mapstruct.Mapper;

@Mapper
interface TrainingMapper {

    TrainingDto toDto(Training training);
}
