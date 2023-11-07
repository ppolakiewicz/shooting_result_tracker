package com.str.shootingresulttracker.domain.kernel;

public class BooleanResult<E extends AbstractBaseDomainError> extends Result<Boolean, E> {

    public static <E extends AbstractBaseDomainError> BooleanResult<E> success() {
        return new BooleanResult<>(true, null);
    }

    public static <E extends AbstractBaseDomainError> BooleanResult<E> fail(E error) {
        return new BooleanResult<>(false, error);
    }

    private BooleanResult(Boolean value, E error) {
        super(value, error);
    }

    public void ifSuccess(SuccessHandler handler) {
        getValue().ifPresent(result -> handler.handle());
    }

    public interface SuccessHandler {
        void handle();
    }

}
