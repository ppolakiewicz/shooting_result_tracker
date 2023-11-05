package com.str.shootingresulttracker.domain.magazine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

interface WeaponRepository extends Repository<Weapon, UUID> {

    @Query("""
            select w
            from Weapon w
            where w.magazine.id = :magazineId
            and w.createdBy = :userId
            and w.magazine.createdBy = :userId
            """)
    List<Weapon> findMagazineWeapons(UUID magazineId, UUID userId);
}
