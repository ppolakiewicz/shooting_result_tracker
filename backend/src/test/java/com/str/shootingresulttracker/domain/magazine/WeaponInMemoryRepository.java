package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.core.AbstractInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WeaponInMemoryRepository extends AbstractInMemoryRepository<Weapon> implements WeaponRepository {

    @Override
    public List<WeaponDto> queryMagazineWeapons(UUID magazineId, UUID userId) {
        return collection.values().stream()
                .filter(weapon -> weapon.getMagazine().getId().equals(magazineId))
                .map(this::toDto)
                .toList();
    }

    @Override
    public Optional<String> queryWeaponName(UUID weaponId, UUID ownerId) {
        return collection.values().stream()
                .filter(weapon -> weapon.getId().equals(weaponId))
                .findFirst()
                .map(Weapon::getName);
    }

    private WeaponDto toDto(Weapon weapon) {
        return new WeaponDto(
                weapon.getId(),
                weapon.getName(),
                weapon.getType(),
                weapon.getCaliber(),
                weapon.getModel(),
                weapon.getProductionDate(),
                weapon.getPurchaseDate(),
                weapon.getCreationDate()
        );
    }
}
