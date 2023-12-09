package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.domain.kernel.BooleanResult;
import com.str.shootingresulttracker.domain.kernel.DomainResult;
import com.str.shootingresulttracker.domain.kernel.EntityDoNotExistsException;
import com.str.shootingresulttracker.domain.magazine.error.FullMagazineError;
import com.str.shootingresulttracker.domain.magazine.error.MagazineWithGivenNameExistsError;
import com.str.shootingresulttracker.domain.magazine.error.MaximumNumberOfMagazines;
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

    private final int MAXIMUM_NUMBER_OF_MAGAZINES = 2;

    private final MagazineRepository repository;
    private final WeaponRepository weaponRepository;
    private final Clock clock;

    @Transactional(readOnly = true)
    public List<MagazineDto> queryAll(UUID userId) {
        return repository.queryAll(userId);
    }

    @Transactional(readOnly = true)
    public MagazineDto query(UUID magazineId, UUID ownerId) {
        return queryMagazine(magazineId, ownerId);
    }

    @Transactional
    public DomainResult<UUID> createMagazine(String magazineName, UUID ownerId) {

        var magazines = repository.queryAll(ownerId);

        if (magazines.size() >= MAXIMUM_NUMBER_OF_MAGAZINES) {
            return new DomainResult<>(new MaximumNumberOfMagazines());
        }

        var magazineWithGivenNameExists = repository.existsByName(magazineName, ownerId);
        if (magazineWithGivenNameExists) {
            return new DomainResult<>(new MagazineWithGivenNameExistsError(magazineName));
        }

        var magazine = new Magazine(magazineName, ownerId, clock);
        magazine = repository.save(magazine);
        log.info("Created magazine {} for user {}", magazine.getId(), magazine.getCreatedBy());
        return new DomainResult<>(magazine.getId());
    }

    @Transactional(readOnly = true)
    public List<WeaponDto> queryMagazineWeapons(UUID magazineId, UUID ownerId) {
        return weaponRepository.queryMagazineWeapons(magazineId, ownerId);
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
    public BooleanResult<MagazineWithGivenNameExistsError> updateMagazine(UUID magazineId, String newMagazineName, UUID ownerId) {
        if (repository.existsByName(newMagazineName, ownerId)) {
            return BooleanResult.fail(new MagazineWithGivenNameExistsError(newMagazineName));
        }

        var magazine = loadMagazine(magazineId, ownerId);
        magazine.setName(newMagazineName);
        repository.save(magazine);

        return BooleanResult.success();
    }

    @Transactional
    public void deleteMagazine(UUID magazineId, UUID ownerId) {
        repository.load(magazineId, ownerId).ifPresent(repository::delete);
    }

    private Magazine loadMagazine(UUID magazineId, UUID ownerId) {
        return repository.load(magazineId, ownerId)
                .orElseThrow(EntityDoNotExistsException::new);
    }

    private MagazineDto queryMagazine(UUID magazineId, UUID ownerId) {
        return repository.queryById(magazineId, ownerId)
                .orElseThrow(EntityDoNotExistsException::new);
    }
}
