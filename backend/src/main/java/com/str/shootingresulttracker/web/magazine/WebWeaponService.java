package com.str.shootingresulttracker.web.magazine;

import com.str.shootingresulttracker.domain.magazine.MagazineService;
import com.str.shootingresulttracker.domain.magazine.WeaponDto;
import com.str.shootingresulttracker.domain.magazine.WeaponService;
import com.str.shootingresulttracker.web.kernel.WebException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
class WebWeaponService {

    private final WeaponService weaponService;
    private final MagazineService magazineService;

    @Transactional(readOnly = true)
    public List<WeaponDto> findMagazineWeapons(UUID magazineId, UUID ownerId) {
        return weaponService.findMagazineWeapons(magazineId, ownerId);
    }

    @Transactional
    public void create(UUID magazineId, WeaponCreateCommand weaponCreateCommand, UUID ownerId) {
        log.debug("Creating new weapon for magazine {}", magazineId);
        var result = magazineService.addWeapon(
                magazineId,
                weaponCreateCommand.weaponName(),
                weaponCreateCommand.type(),
                weaponCreateCommand.caliber(),
                weaponCreateCommand.model(),
                weaponCreateCommand.productionDate(),
                weaponCreateCommand.purchaseDate(),
                ownerId
        );

        result.ifError(fullMagazineError -> {
            log.warn("Could not add weapon for magazine cause magazine is full");
            throw WebException.fromDomainError(fullMagazineError);
        });
    }
}
