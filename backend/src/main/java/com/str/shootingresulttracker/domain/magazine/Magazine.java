package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.domain.magazine.error.FullMagazineError;
import com.str.shootingresulttracker.domain.model.AbstractBaseAggregate;
import com.str.shootingresulttracker.domain.model.AbstractBaseDomainError;
import com.str.shootingresulttracker.domain.model.BooleanResult;
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
import static java.util.Optional.ofNullable;

@Getter
@Entity
@Table(name = "srt_magazine")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Magazine extends AbstractBaseAggregate {

    private static final int DEFAULT_CAPACITY = 50;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "capacity")
    private int capacity;

    @NotNull
    @Column(name = "weapon_count")
    private int weaponCount;

    @OneToMany(mappedBy = "magazine", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Weapon> weapons;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "ammunition")
    private Set<Ammunition> ammunition;

    public Magazine(String name, UUID createdBy, Clock clock) {
        super(clock, createdBy);
        requiredNonEmpty(name, "name");

        this.name = name;
        this.capacity = DEFAULT_CAPACITY;
        this.weaponCount = 0;
        this.weapons = new HashSet<>();
        this.ammunition = new HashSet<>();
    }

    public Set<Ammunition> getAmmunition() {
        return Collections.unmodifiableSet(ammunition);
    }

    public BooleanResult<FullMagazineError> addWeapon(Weapon weapon) {
        Objects.requireNonNull(weapon, "Weapon for magazine can not be null");

        if (isMagazineFull()) {
            return BooleanResult.fail(new FullMagazineError(capacity));
        }

        this.weapons.add(weapon);
        weapon.setMagazine(this);
        this.weaponCount++;

        return BooleanResult.success();
    }

    public BooleanResult<AbstractBaseDomainError> addAmmunition(Ammunition addedAmmunition) {

        Objects.requireNonNull(addedAmmunition, "Added ammunition can not be null");

        var currentAmmunition = ammunition.stream()
                .filter(ammo -> ammo.caliber().equals(addedAmmunition.caliber()))
                .findFirst()
                .orElse(Ammunition.empty(addedAmmunition.caliber()));

        var ammunitionAddResult = currentAmmunition.addQuantity(addedAmmunition.quantity());

        if (ammunitionAddResult.isError()) {
            return BooleanResult.fail(ammunitionAddResult.getError());
        }

        ammunitionAddResult.ifValue(updatedAmmunition -> replaceAmmunition(currentAmmunition, updatedAmmunition));
        return BooleanResult.success();
    }

    public BooleanResult<AbstractBaseDomainError> subtractAmmunition(Ammunition subtractedAmmunition) {

        Objects.requireNonNull(subtractedAmmunition, "Subtracted ammunition can not be null");

        var currentAmmunition = ammunition.stream()
                .filter(ammo -> ammo.caliber().equals(subtractedAmmunition.caliber()))
                .findFirst()
                .orElse(Ammunition.empty(subtractedAmmunition.caliber()));

        var ammunitionSubtractResult = currentAmmunition.subtractQuantity(subtractedAmmunition.quantity());

        if (ammunitionSubtractResult.isError()) {
            return BooleanResult.fail(ammunitionSubtractResult.getError());
        }

        ofNullable(ammunitionSubtractResult.getValue())
                .filter(updatedAmmunition -> updatedAmmunition.quantity() > 0)
                .ifPresentOrElse(
                        updatedAmmunition -> replaceAmmunition(currentAmmunition, updatedAmmunition),
                        () -> removeAmmunition(currentAmmunition)
                );

        return BooleanResult.success();
    }

    public void setName(String newMagazineName) {
        requiredNonEmpty(newMagazineName, "name");
        this.name = newMagazineName;
    }

    private boolean isMagazineFull() {
        return capacity >= weaponCount;
    }

    private void replaceAmmunition(Ammunition current, Ammunition newValue) {
        this.ammunition.remove(current);
        this.ammunition.add(newValue);
    }

    private void removeAmmunition(Ammunition ammunition) {
        this.ammunition.remove(ammunition);
    }

}
