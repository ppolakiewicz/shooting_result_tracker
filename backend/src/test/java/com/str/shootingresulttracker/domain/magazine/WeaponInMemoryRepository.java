package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.core.AbstractInMemoryRepository;

import java.util.List;
import java.util.UUID;

public class WeaponInMemoryRepository extends AbstractInMemoryRepository<Weapon> implements WeaponRepository {

    @Override
    public List<WeaponDto> queryMagazineWeapons(UUID magazineId, UUID userId) {
        return null;
    }
}
