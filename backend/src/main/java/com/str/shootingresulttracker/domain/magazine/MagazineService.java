package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.domain.kernel.BooleanResult;
import com.str.shootingresulttracker.domain.kernel.EntityDoNotExistsException;
import com.str.shootingresulttracker.domain.kernel.Result;
import com.str.shootingresulttracker.domain.magazine.error.FullMagazineError;
import com.str.shootingresulttracker.domain.magazine.error.MagazineWithGivenNameExistsError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class MagazineService {

    private final MagazineRepository repository;
    private final MagazineMapper mapper;
    private final Clock clock;

    @Transactional(readOnly = true)
    public List<MagazineDto> loadAll(UUID userId) {
        return repository.findAll(userId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public Result<MagazineDto, MagazineWithGivenNameExistsError> createMagazine(String magazineName, UUID ownerId) {

        if (repository.existsByName(magazineName, ownerId)) {
            return new Result<>(new MagazineWithGivenNameExistsError(magazineName));
        }

        var magazine = new Magazine(magazineName, ownerId, clock);
        log.info("Created magazine {} for user {}", magazine.getId(), magazine.getCreatedBy());
        return new Result<>(mapper.toDto(repository.save(magazine)));
    }

    @Transactional
    public BooleanResult<FullMagazineError> addWeapon(
            UUID magazineId,
            String weaponName,
            WeaponType type,
            Caliber caliber,
            String model,
            OffsetDateTime productionDate,
            OffsetDateTime purchaseDate,
            UUID createdBy) {

        var weapon = new Weapon(weaponName, type, caliber, model, productionDate, purchaseDate, createdBy, clock);
        var magazine = loadMagazine(magazineId, createdBy);

        var result = magazine.addWeapon(weapon);
        result.ifSuccess(() -> repository.save(magazine));

        return result;
    }

    @Transactional
    public Result<MagazineDto, MagazineWithGivenNameExistsError> updateMagazine(UUID magazineId, String newMagazineName, UUID ownerId) {
        if (repository.existsByName(newMagazineName, ownerId)) {
            return new Result<>(new MagazineWithGivenNameExistsError(newMagazineName));
        }

        var magazine = loadMagazine(magazineId, ownerId);
        magazine.setName(newMagazineName);

        return new Result<>(mapper.toDto(repository.save(magazine)));
    }

    @Transactional
    public void deleteMagazine(UUID magazineId, UUID ownerId) {
        repository.findById(magazineId, ownerId).ifPresent(repository::delete);
    }

    private <V> Magazine loadMagazine(UUID magazineId, UUID ownerId) {
        return repository.findById(magazineId, ownerId)
                .orElseThrow(EntityDoNotExistsException::new);
    }
}
