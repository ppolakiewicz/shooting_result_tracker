package srt.kernel.exception;

public abstract class AbstractBaseDomainException extends RuntimeException {

    private final String errorKey;

    public AbstractBaseDomainException(String message, String errorKey) {
        super(message);
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return errorKey;
    }
}
