package com.str.shootingresulttracker.domain.magazine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WeaponService {

    private final WeaponRepository repository;

    @Transactional(readOnly = true)
    public List<Weapon> findMagazineWeapons(UUID magazineId, UUID userId){
        return repository.findMagazineWeapons(magazineId, userId);
    }
}
