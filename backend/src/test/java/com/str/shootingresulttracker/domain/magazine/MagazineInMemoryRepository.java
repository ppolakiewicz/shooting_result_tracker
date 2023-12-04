package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.core.AbstractInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class MagazineInMemoryRepository extends AbstractInMemoryRepository<Magazine> implements MagazineRepository {

    @Override
    public Optional<Magazine> load(UUID magazineId, UUID ownerId) {
        return collection.values().stream()
                .filter(magazine -> magazine.getId() == magazineId)
                .filter(magazine -> magazine.getCreatedBy() == ownerId)
                .findFirst();
    }

    @Override
    public List<MagazineDto> queryAll(UUID ownerId) {
        return collection.values().stream()
                .filter(magazine -> magazine.getCreatedBy() == ownerId)
                .map(this::toDto)
                .toList();
    }

    @Override
    public Optional<MagazineDto> queryById(UUID magazineId, UUID ownerId) {
        return load(magazineId, ownerId).map(this::toDto);
    }

    @Override
    public boolean existsByName(String magazineName, UUID ownerId) {
        return collection.values().stream()
                .anyMatch(magazine -> magazine.getName().equalsIgnoreCase(magazineName));
    }

    private MagazineDto toDto(Magazine magazine) {
        return new MagazineDto(
                magazine.getId(),
                magazine.getName(),
                magazine.getCapacity(),
                magazine.getWeaponCount(),
                magazine.getAmmunition(),
                magazine.getCreationDate()
        );
    }
}
