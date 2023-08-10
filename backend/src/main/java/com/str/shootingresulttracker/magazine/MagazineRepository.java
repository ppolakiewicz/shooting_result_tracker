package com.str.shootingresulttracker.magazine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface MagazineRepository extends JpaRepository<Magazine, UUID> {
}
