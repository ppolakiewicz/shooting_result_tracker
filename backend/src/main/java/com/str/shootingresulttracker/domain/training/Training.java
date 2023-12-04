package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.kernel.AbstractBaseAggregate;
import com.str.shootingresulttracker.domain.kernel.BooleanResult;
import com.str.shootingresulttracker.domain.training.error.FullTrainingError;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.str.shootingresulttracker.kernel.StringUtils.requiredNonEmpty;
import static java.util.Objects.requireNonNull;

@Getter
@Entity
@Table(name = "srt_training")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Training extends AbstractBaseAggregate {

    private static final int TRAINING_RESULT_LIMIT = 5;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "session_date")
    private OffsetDateTime sessionDate;

    @Column(name = "place")
    private String place;

    @Column(name = "result_count")
    private int resultCount;

    @OneToMany(mappedBy = "training", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrainingResult> results;

    public Training(Clock clock, UUID createdBy, String name, OffsetDateTime sessionDate, String place) {
        super(clock, createdBy);
        requiredNonEmpty(name, "name");
        requireNonNull(sessionDate, "Training session date is required");

        this.name = name;
        this.sessionDate = sessionDate;
        this.place = place;
        this.resultCount = 0;
        this.results = new HashSet<>();
    }

    public BooleanResult<FullTrainingError> addResult(TrainingResult result) {
        requireNonNull(result, "Result can not be null");

        if (resultCount == TRAINING_RESULT_LIMIT) {
            return BooleanResult.fail(new FullTrainingError(TRAINING_RESULT_LIMIT));
        }

        this.resultCount++;
        this.results.add(result);
        result.setTraining(this);

        return BooleanResult.success();
    }

}
