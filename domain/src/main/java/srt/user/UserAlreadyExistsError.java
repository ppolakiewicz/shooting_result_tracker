package srt.user;

import srt.kernel.AbstractBaseDomainError;

class UserAlreadyExistsError extends AbstractBaseDomainError {

    public UserAlreadyExistsError(String email) {
        super("user.error.alreadyExists",
                "User with given email: " + email + "already exists");
    }

}
