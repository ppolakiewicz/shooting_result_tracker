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
            and m.createdBy = :userId
            """)
    Optional<Magazine> findById(UUID magazineId, UUID userId);

    @Query("""
            select m
            from Magazine m
            where m.createdBy = :userId
            """)
    List<Magazine> findAll(UUID userId);

    @Query("""
            select case when count(m) > 0 then true else false end 
            from Magazine m 
            where lower(m.name) = lower(:magazineName)
            and m.createdBy = :userId
            """)
    boolean existsByName(String magazineName, UUID userId);
}
