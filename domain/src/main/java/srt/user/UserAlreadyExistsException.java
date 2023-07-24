package srt.user;

import srt.kernel.exception.AbstractBaseDomainException;

class UserAlreadyExistsException extends AbstractBaseDomainException {

    public UserAlreadyExistsException(String email) {
        super("User with given email: " + email + "already exists",
                "user.exception.alreadyExists"
        );
    }
}
