package com.str.shootingresulttracker.domain.training;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
@AllArgsConstructor
public class TrainingService {

    private final TrainingMapper mapper;
    private final TrainingRepository repository;
    private final Clock clock;

//    @Transactional
//    public DomainResult<TrainingDto> create(String name, OffsetDateTime sessionDate, String place, int shotsFired, UUID ownerId){
//
//        //var training = new Training(clock, ownerId, name, sessionDate, place, shotsFired);
//    }

}
