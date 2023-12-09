package com.str.shootingresulttracker.domain.magazine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WeaponService {

    private final WeaponRepository repository;

    public Optional<String> queryWeaponName(UUID weaponId, UUID ownerId) {
        return repository.queryWeaponName(weaponId, ownerId);
    }
}
