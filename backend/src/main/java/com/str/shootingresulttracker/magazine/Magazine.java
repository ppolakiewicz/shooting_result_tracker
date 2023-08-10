package com.str.shootingresulttracker.magazine;

import com.str.shootingresulttracker.kernel.AbstractBaseAggregate;
import com.str.shootingresulttracker.kernel.Result;
import com.str.shootingresulttracker.weapon.Weapon;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Weapon> weapons;

    public Magazine(String name, UUID ownerId, Clock clock) {
        super(clock);
        Objects.requireNonNull(name, "Magazine name can not be null");
        Objects.requireNonNull(ownerId, "Magazine owner can not be null");

        this.name = name;
        this.ownerId = ownerId;
        this.capacity = DEFAULT_CAPACITY;
        this.weapons = new HashSet<>();
    }

    public Set<Weapon> getWeapons() {
        return Collections.unmodifiableSet(this.weapons);
    }

    public Result<Boolean, FullMagazineError> addWeapon(Weapon weapon) {
        Objects.requireNonNull(weapon, "Weapon for magazine can not be null");

        if (magazineIsFull()) {
            return new Result<>(new FullMagazineError(capacity));
        }

        this.weapons.add(weapon);
        weapon.setMagazine(this);
        return new Result<>(true);
    }

    private boolean magazineIsFull() {
        return capacity == weapons.size();
    }

}
