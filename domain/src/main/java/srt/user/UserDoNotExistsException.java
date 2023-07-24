package srt.user;

import srt.kernel.exception.AbstractBaseDomainException;

class UserDoNotExistsException extends AbstractBaseDomainException {

    public UserDoNotExistsException(String email) {
        super("User with email: " + email + " do not exists",
                "user.exception.doNotExists"
        );
    }
}
