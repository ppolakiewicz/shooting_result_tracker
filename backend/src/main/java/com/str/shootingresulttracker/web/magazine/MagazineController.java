package com.str.shootingresulttracker.web.magazine;

import com.str.shootingresulttracker.domain.magazine.MagazineDto;
import com.str.shootingresulttracker.domain.magazine.MagazineService;
import com.str.shootingresulttracker.usermanagment.UserDto;
import com.str.shootingresulttracker.web.kernel.WebException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/magazine")
@AllArgsConstructor
class MagazineController {

    private final MagazineService service;

    @GetMapping
    List<MagazineDto> findAll(@AuthenticationPrincipal UserDto principal) {
        return service.queryAll(principal.getId());
    }

    @PostMapping
    MagazineDto create(@RequestBody MagazineCreateCommand command, @AuthenticationPrincipal UserDto principal) {
        var magazineId = service.createMagazine(command.name(), principal.getId()).orElseThrow(WebException::fromDomainError);
        return service.query(magazineId, principal.getId());
    }

    @PutMapping("/{magazineId}")
    MagazineDto update(@PathVariable UUID magazineId, @RequestBody String name, @AuthenticationPrincipal UserDto principal){
        service.updateMagazine(magazineId, name, principal.getId()).orElseThrow(WebException::fromDomainError);
        return service.query(magazineId, principal.getId());
    }

    @DeleteMapping("/{magazineId}")
    void delete(@PathVariable UUID magazineId, @AuthenticationPrincipal UserDto principal){
        service.deleteMagazine(magazineId, principal.getId());
    }

}
