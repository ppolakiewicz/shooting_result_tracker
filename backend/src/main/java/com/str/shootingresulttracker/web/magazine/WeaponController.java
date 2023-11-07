package com.str.shootingresulttracker.web.magazine;

import com.str.shootingresulttracker.domain.magazine.WeaponDto;
import com.str.shootingresulttracker.domain.magazine.WeaponService;
import com.str.shootingresulttracker.usermanagment.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/magazine/{magazineId}/weapon")
@AllArgsConstructor
class WeaponController {

    private final WeaponService service;

    @GetMapping
    public List<WeaponDto> findAll(@PathVariable UUID magazineId, @AuthenticationPrincipal UserDto principal) {
        return service.findMagazineWeapons(magazineId, principal.getId());
    }


}
