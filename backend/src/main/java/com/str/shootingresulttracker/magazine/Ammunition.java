package com.str.shootingresulttracker.magazine;

import com.str.shootingresulttracker.kernel.Result;
import com.str.shootingresulttracker.magazine.error.AmmunitionQuantityError;
import com.str.shootingresulttracker.weapon.Caliber;

import java.util.Objects;

public record Ammunition(
        Caliber caliber,
        long quantity
) {

    public static Ammunition empty(Caliber caliber) {
        return new Ammunition(caliber, 0L);
    }

    public Ammunition {
        Objects.requireNonNull(caliber, "Ammunition can not have empty caliber");
        if (quantity < 0) {
            throw new IllegalArgumentException("Ammunition can not be initialized with negative quantity");
        }
    }

    public Result<Ammunition, AmmunitionQuantityError> addQuantity(long addedQuantity) {

        if (addedQuantity < 0) {
            return new Result<>(new AmmunitionQuantityError(addedQuantity));
        }

        return new Result<>(new Ammunition(
                this.caliber,
                this.quantity + addedQuantity
        ));
    }

    public Result<Ammunition, AmmunitionQuantityError> subtractQuantity(long subtractedQuantity) {

        if (subtractedQuantity < 0) {
            return new Result<>(new AmmunitionQuantityError(subtractedQuantity));
        }

        return new Result<>(new Ammunition(
                this.caliber,
                Math.max(this.quantity - subtractedQuantity, 0)
        ));
    }

}
