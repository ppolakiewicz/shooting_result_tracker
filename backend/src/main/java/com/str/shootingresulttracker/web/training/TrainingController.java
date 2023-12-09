package com.str.shootingresulttracker.web.training;

import com.str.shootingresulttracker.domain.kernel.DomainResult;
import com.str.shootingresulttracker.domain.magazine.WeaponService;
import com.str.shootingresulttracker.domain.training.TrainingDto;
import com.str.shootingresulttracker.domain.training.TrainingResultDto;
import com.str.shootingresulttracker.domain.training.TrainingService;
import com.str.shootingresulttracker.usermanagment.UserDto;
import com.str.shootingresulttracker.web.kernel.WebException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/training")
@AllArgsConstructor
class TrainingController {

    private final TrainingService service;
    private final WeaponService weaponService;

    @GetMapping
    List<TrainingDto> findAll(@AuthenticationPrincipal UserDto principal) {
        return service.queryAll(principal.getId());
    }

    @GetMapping("/{trainingId}")
    TrainingDto find(@PathVariable UUID trainingId, @AuthenticationPrincipal UserDto principal) {
        return service.queryById(trainingId, principal.getId()).orElseThrow(TrainingDoNotExistsWebException::new);
    }

    @PostMapping
    TrainingDto create(@RequestBody TrainingCreateCommand command, @AuthenticationPrincipal UserDto principal) {
        DomainResult<UUID> result = service.create(
                command.name(),
                command.sessionDate(),
                command.place(),
                principal.getId()
        );

        result.orElseThrow(WebException::fromDomainError);

        return service.queryById(result.getValue(), principal.getId()).orElseThrow(TrainingDoNotExistsWebException::new);
    }

    @GetMapping("/{trainingId}/result")
    List<TrainingResultDto> findAllResults(@PathVariable UUID trainingId, @AuthenticationPrincipal UserDto principal) {
        return service.queryTrainingResults(trainingId, principal.getId());
    }

    @PostMapping("/{trainingId}/result")
    void createResult(@PathVariable UUID trainingId, @RequestBody TrainingResultCreateCommand command, @AuthenticationPrincipal UserDto principal) {

        var weaponName = weaponService.queryWeaponName(command.weaponId(), principal.getId())
                .orElseThrow(TrainingWeaponDoNotExistsWebException::new);

        service.addTrainingResult(trainingId, command.weaponId(), weaponName, principal.getId())
                .orElseThrow(WebException::fromDomainError);
    }

}