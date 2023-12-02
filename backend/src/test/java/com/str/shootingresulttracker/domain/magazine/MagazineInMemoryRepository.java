package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.core.AbstractInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class MagazineInMemoryRepository extends AbstractInMemoryRepository<Magazine> implements MagazineRepository {

    @Override
    public Optional<Magazine> findById(UUID magazineId, UUID ownerId) {
        return Optional.ofNullable(collection.get(magazineId));
    }

    @Override
    public List<Magazine> findAll(UUID ownerId) {
        return collection.values().stream().toList();
    }

    @Override
    public boolean existsByName(String magazineName, UUID ownerId) {
        return collection.values().stream()
                .anyMatch(magazine -> magazine.getName().equalsIgnoreCase(magazineName));
    }
}
