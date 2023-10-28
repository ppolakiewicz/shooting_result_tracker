package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.domain.kernel.AbstractBaseDomainError;

public class UserDoNotExistsError extends AbstractBaseDomainError {

    public UserDoNotExistsError(String email) {
        super("user.error.doNotExists",
                "User with email: " + email + " do not exists");
    }

}
