package com.str.shootingresulttracker.magazine;

import com.str.shootingresulttracker.kernel.AbstractBaseAggregate;
import com.str.shootingresulttracker.kernel.Result;
import com.str.shootingresulttracker.magazine.error.AmmunitionQuantityError;
import com.str.shootingresulttracker.magazine.error.FullMagazineError;
import com.str.shootingresulttracker.weapon.Weapon;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Clock;
import java.util.*;

@Getter
@Entity
@Table(name = "magazine")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Magazine extends AbstractBaseAggregate {

    private static final int DEFAULT_CAPACITY = 50;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "owner_id")
    private UUID ownerId;

    @NotNull
    @Column(name = "capacity")
    private int capacity;

    @NotNull
    @Column(name = "weapon_count")
    private int weaponCount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Weapon> weapons;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "ammunition")
    private Set<Ammunition> ammunitions;

    public Magazine(String name, UUID ownerId, Clock clock) {
        super(clock);
        Objects.requireNonNull(name, "Magazine name can not be null");
        Objects.requireNonNull(ownerId, "Magazine owner can not be null");

        this.name = name;
        this.ownerId = ownerId;
        this.capacity = DEFAULT_CAPACITY;
        this.weaponCount = 0;
        this.weapons = new HashSet<>();
        this.ammunitions = new HashSet<>();
    }

    public Set<Weapon> getWeapons() {
        return Collections.unmodifiableSet(weapons);
    }

    public Set<Ammunition> getAmmunitions() {
        return Collections.unmodifiableSet(ammunitions);
    }

    public Result<Boolean, FullMagazineError> addWeapon(Weapon weapon) {
        Objects.requireNonNull(weapon, "Weapon for magazine can not be null");

        if (isMagazineFull()) {
            return new Result<>(new FullMagazineError(capacity));
        }

        this.weapons.add(weapon);
        weapon.setMagazine(this);
        this.weaponCount++;

        return new Result<>(true);
    }

    public Result<Boolean, AmmunitionQuantityError> addAmmunition(Ammunition addedAmmunition) {

        var currentAmmunition = ammunitions.stream()
                .filter(ammo -> ammo.caliber().equals(addedAmmunition.caliber()))
                .findFirst()
                .orElse(Ammunition.empty(addedAmmunition.caliber()));

        var ammunitionAddResult = currentAmmunition.addQuantity(addedAmmunition.quantity());

        if (ammunitionAddResult.getError().isPresent()) {
            return new Result<>(ammunitionAddResult.getError().get());
        }

        ammunitionAddResult.getValue()
                .ifPresent(updatedAmmunition -> replaceAmmunition(currentAmmunition, updatedAmmunition));

        return new Result<>(true);
    }

    public Result<Boolean, AmmunitionQuantityError> subtractAmmunition(Ammunition subtractedAmmunition) {

        var currentAmmunition = ammunitions.stream()
                .filter(ammo -> ammo.caliber().equals(subtractedAmmunition.caliber()))
                .findFirst()
                .orElse(Ammunition.empty(subtractedAmmunition.caliber()));

        var ammunitionSubtractResult = currentAmmunition.subtractQuantity(subtractedAmmunition.quantity());

        if (ammunitionSubtractResult.getError().isPresent()) {
            return new Result<>(ammunitionSubtractResult.getError().get());
        }

        ammunitionSubtractResult.getValue()
                .filter(updatedAmmunition -> updatedAmmunition.quantity() > 0)
                .ifPresentOrElse(
                        updatedAmmunition -> replaceAmmunition(currentAmmunition, updatedAmmunition),
                        () -> removeAmmunition(currentAmmunition)
                );

        return new Result<>(true);
    }

    private boolean isMagazineFull() {
        return capacity >= weaponCount;
    }

    private void replaceAmmunition(Ammunition current, Ammunition newValue) {
        this.ammunitions.remove(current);
        this.ammunitions.add(newValue);
    }

    private void removeAmmunition(Ammunition ammunition) {
        this.ammunitions.remove(ammunition);
    }

}
