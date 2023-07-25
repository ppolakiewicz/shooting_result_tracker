package srt.user;

import srt.kernel.AbstractBaseDomainError;

class UserDoNotExistsError extends AbstractBaseDomainError {

    public UserDoNotExistsError(String email) {
        super("user.error.doNotExists",
                "User with email: " + email + " do not exists");
    }

}
