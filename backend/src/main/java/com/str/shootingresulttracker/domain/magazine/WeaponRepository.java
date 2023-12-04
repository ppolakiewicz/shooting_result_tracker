package com.str.shootingresulttracker.domain.magazine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

interface WeaponRepository extends Repository<Weapon, UUID> {

    @Query("""
            select new com.str.shootingresulttracker.domain.magazine.WeaponDto(
            w.id,
            w.name,
            w.type,
            w.caliber,
            w.model,
            w.productionDate,
            w.purchaseDate,
            w.creationDate
            )
            from Weapon w
            where w.magazine.id = :magazineId
            and w.createdBy = :userId
            and w.magazine.createdBy = :userId
            """)
    List<WeaponDto> queryMagazineWeapons(UUID magazineId, UUID userId);
}
