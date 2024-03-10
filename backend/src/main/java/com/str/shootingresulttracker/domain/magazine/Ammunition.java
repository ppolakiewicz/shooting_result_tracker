package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.domain.magazine.error.AmmunitionQuantityError;
import com.str.shootingresulttracker.domain.model.DomainResult;

import java.util.Objects;

public record Ammunition(
        Caliber caliber,
        long quantity
) {

    public Ammunition {
        Objects.requireNonNull(caliber, "Ammunition can not have empty caliber");
        if (quantity < 0) {
            throw new IllegalArgumentException("Ammunition can not be initialized with negative quantity");
        }
    }

    public static Ammunition empty(Caliber caliber) {
        return new Ammunition(caliber, 0L);
    }

    public DomainResult<Ammunition> addQuantity(long addedQuantity) {

        if (addedQuantity < 0) {
            return new DomainResult<>(new AmmunitionQuantityError(addedQuantity));
        }

        return new DomainResult<>(new Ammunition(
                this.caliber,
                this.quantity + addedQuantity
        ));
    }

    public DomainResult<Ammunition> subtractQuantity(long subtractedQuantity) {

        if (subtractedQuantity < 0) {
            return new DomainResult<>(new AmmunitionQuantityError(subtractedQuantity));
        }

        return new DomainResult<>(new Ammunition(
                this.caliber,
                Math.max(this.quantity - subtractedQuantity, 0)
        ));
    }

}
