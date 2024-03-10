package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.domain.model.AbstractBaseDomainError;

public class UserAlreadyExistsError extends AbstractBaseDomainError {

    public UserAlreadyExistsError(String email) {
        super("user.error.alreadyExists",
                "User with given email: " + email + "already exists");
    }

}
