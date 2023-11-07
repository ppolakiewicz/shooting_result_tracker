package com.str.shootingresulttracker.usermanagment;

import java.util.Collection;
import java.util.List;

public enum UserPermission {
    ADMIN,
    MAGAZINE,
    WEAPON;

    public static final Collection<UserPermission> DEFAULT_PERMISSIONS = List.of(
            MAGAZINE, WEAPON
    );
}