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
        return service.loadAll(principal.getId());
    }

    @PostMapping
    MagazineDto create(@RequestBody String name, @AuthenticationPrincipal UserDto principal) {
        return service.createMagazine(name, principal.getId())
                .orElseThrow(WebException::fromDomainError);
    }

    @PutMapping("/{magazineId}")
    MagazineDto update(@PathVariable UUID magazineId, @RequestBody String name, @AuthenticationPrincipal UserDto principal){
        return service.updateMagazine(magazineId, name, principal.getId())
                .orElseThrow(WebException::fromDomainError);
    }

    @DeleteMapping("/{magazineId}")
    void delete(@PathVariable UUID magazineId, @AuthenticationPrincipal UserDto principal){
        service.deleteMagazine(magazineId, principal.getId());
    }

}
