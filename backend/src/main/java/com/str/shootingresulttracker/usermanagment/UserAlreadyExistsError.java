package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.kernel.AbstractBaseDomainError;

public class UserAlreadyExistsError extends AbstractBaseDomainError {

    public UserAlreadyExistsError(String email) {
        super("user.error.alreadyExists",
                "User with given email: " + email + "already exists");
    }

}
