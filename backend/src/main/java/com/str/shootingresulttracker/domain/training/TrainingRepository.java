package com.str.shootingresulttracker.domain.training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TrainingRepository extends JpaRepository<Training, UUID> {
}
