package com.str.shootingresulttracker.domain.magazine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface MagazineRepository extends JpaRepository<Magazine, UUID> {

    @Query("""
            select m
            from Magazine m
            where m.id = :magazineId
            and m.createdBy = :ownerId
            """)
    Optional<Magazine> load(UUID magazineId, UUID ownerId);

    @Query("""
            select new com.str.shootingresulttracker.domain.magazine.MagazineDto(
            m.id,
            m.name,
            m.capacity,
            m.weaponCount,
            m.ammunition,
            m.creationDate
            )
            from Magazine m
            where m.createdBy = :ownerId
            """)
    List<MagazineDto> queryAll(UUID ownerId);

    @Query("""
            select new com.str.shootingresulttracker.domain.magazine.MagazineDto(
                        m.id,
                        m.name,
                        m.capacity,
                        m.weaponCount,
                        m.ammunition,
                        m.creationDate
                        )
                        from Magazine m
                        where m.id = :magazineId
                        and m.createdBy = :ownerId
                        """)
    Optional<MagazineDto> queryById(UUID magazineId, UUID ownerId);

    @Query("""
            select case when count(m) > 0 then true else false end
            from Magazine m
            where lower(m.name) = lower(:magazineName)
            and m.createdBy = :ownerId
            """)
    boolean existsByName(String magazineName, UUID ownerId);
}
