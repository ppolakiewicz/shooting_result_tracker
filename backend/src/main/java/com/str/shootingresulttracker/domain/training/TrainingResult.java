package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.kernel.AbstractBaseDomainEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Clock;
import java.util.*;

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

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "shots_results")
    private List<Integer> shotsResults;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "files_ids")
    private Set<Long> filesIds;


    public TrainingResult(Clock clock, UUID createdBy, UUID weaponId, String weaponName) {
        this(clock, createdBy, weaponId, weaponName, List.of());
    }

    public TrainingResult(Clock clock, UUID createdBy, UUID weaponId, String weaponName, List<Integer> shotsResults) {
        super(clock, createdBy);
        requireNonNull(weaponId, "Weapon ID is required");
        requiredNonEmpty(weaponName, "Weapon name");

        this.weaponId = weaponId;
        this.weaponName = weaponName;
        this.shotsResults = shotsResults == null ? new ArrayList<>() : shotsResults;
        this.filesIds = new HashSet<>();
    }

    public void changeWeapon(UUID weaponId, String weaponName) {
        requireNonNull(weaponId, "Weapon ID is required");
        requiredNonEmpty(weaponName, "Weapon name");

        this.weaponId = weaponId;
        this.weaponName = weaponName;
    }

    public void addFile(Long fileId) {
        requireNonNull(fileId, "File id can not be null");
        filesIds.add(fileId);
    }

    public void setTraining(Training training) {
        requireNonNull(training, "Training can not be null");
        this.training = training;
    }

    public List<Integer> getShotsResults() {
        return new ArrayList<>(shotsResults);
    }

    public void setShotsResults(List<Integer> shotsResults) {
        requireNonNull(shotsResults, "Shots result can not be empty");
        this.shotsResults = shotsResults;
    }

    public Set<Long> getFilesIds() {
        return new HashSet<>(filesIds);
    }
}
