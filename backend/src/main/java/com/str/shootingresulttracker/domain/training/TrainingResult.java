package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.infrastructure.DistanceConverter;
import com.str.shootingresulttracker.domain.kernel.AbstractBaseDomainEntity;
import com.str.shootingresulttracker.domain.model.Distance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

import static com.str.shootingresulttracker.kernel.StringUtils.requiredNonEmpty;
import static java.util.Objects.requireNonNull;


@Entity
@Table(name = "srt_training_result")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class TrainingResult extends AbstractBaseDomainEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    private Training training;

    @Getter
    @Column(name = "weapon_id")
    private UUID weaponId;

    @Getter
    @Column(name = "weapon_name")
    private String weaponName;

    @Getter
    @Column(name = "distance")
    @Convert(converter = DistanceConverter.class)
    private Distance distance;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "shots_results")
    private List<ShootResult> shotsResults;

    public TrainingResult(Clock clock, UUID createdBy, UUID weaponId, String weaponName, List<ShootResult> shotsResults, Distance distance) {
        super(clock, createdBy);
        requireNonNull(weaponId, "Weapon ID is required");
        requiredNonEmpty(weaponName, "Weapon name");
        requireNonNull(distance, "Training distance is required");

        this.weaponId = weaponId;
        this.weaponName = weaponName;
        this.distance = distance;
        this.shotsResults = shotsResults == null ? List.of() : List.copyOf(shotsResults);
    }

    public void changeWeapon(UUID weaponId, String weaponName) {
        requireNonNull(weaponId, "Weapon ID is required");
        requiredNonEmpty(weaponName, "Weapon name");

        this.weaponId = weaponId;
        this.weaponName = weaponName;
    }

    public void setTraining(Training training) {
        requireNonNull(training, "Training can not be null");
        this.training = training;
    }

    public List<ShootResult> getShotsResults() {
        return List.copyOf(shotsResults);
    }

    public void setShotsResults(List<ShootResult> shotsResults) {
        requireNonNull(shotsResults, "Shots result can not be empty");
        this.shotsResults = List.copyOf(shotsResults);
    }

}
