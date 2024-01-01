package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.infrastructure.DistanceConverter;
import com.str.shootingresulttracker.domain.infrastructure.MoaFocusConverter;
import com.str.shootingresulttracker.domain.kernel.AbstractBaseDomainEntity;
import com.str.shootingresulttracker.domain.model.Distance;
import com.str.shootingresulttracker.domain.training.moa.MoaFocus;
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

    @Column(name = "focus_result")
    @Convert(converter = MoaFocusConverter.class)
    private MoaFocus focusResult;

    public TrainingResult(Clock clock, UUID createdBy, UUID weaponId, String weaponName, List<Integer> shotsResults, Distance shootingDistance) {
        super(clock, createdBy);
        requireNonNull(weaponId, "Weapon ID is required");
        requiredNonEmpty(weaponName, "Weapon name is required");
        requireNonNull(shootingDistance, "Training distance is required");

        this.weaponId = weaponId;
        this.weaponName = weaponName;
        this.distance = shootingDistance;
        this.shotsResults = shotsResults == null ? List.of() : shotsResults.stream().map(ShootResult::simpleResult).toList();
        this.focusResult = MoaFocus.zero();
    }

    public TrainingResult(Clock clock, UUID createdBy, UUID weaponId, String weaponName, Distance shootingDistance, List<ShootResult> shotsResults, ReferenceScale referenceScale) {
        super(clock, createdBy);
        requireNonNull(weaponId, "Weapon ID is required");
        requiredNonEmpty(weaponName, "Weapon name is required");
        requireNonNull(shootingDistance, "Training distance is required");
        requireNonNull(referenceScale, "ReferenceScale is required");

        this.weaponId = weaponId;
        this.weaponName = weaponName;
        this.distance = shootingDistance;
        this.shotsResults = shotsResults == null ? List.of() : List.copyOf(shotsResults);
        this.focusResult = MoaFocus.calculate(distance, referenceScale, shotsResults);
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
