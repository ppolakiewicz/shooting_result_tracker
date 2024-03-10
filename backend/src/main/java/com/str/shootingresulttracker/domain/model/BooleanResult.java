package com.str.shootingresulttracker.domain.model;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class BooleanResult<E extends AbstractBaseDomainError> {

    private final boolean value;
    private final E error;

    private BooleanResult(boolean value, E error) {
        this.value = value;
        this.error = error;
    }

    public static <E extends AbstractBaseDomainError> BooleanResult<E> success() {
        return new BooleanResult<>(true, null);
    }

    public static <E extends AbstractBaseDomainError> BooleanResult<E> fail(E error) {
        return new BooleanResult<>(false, error);
    }

    public E getError() {
        return Optional.ofNullable(error).orElseThrow(() -> new NullPointerException("This boolean result do not contains error"));
    }

    public void ifSuccess(SuccessHandler handler) {
        if (value) {
            handler.handle();
        }
    }

    public void ifFail(Consumer<? super E> errorConsumer) {
        if (!value) {
            errorConsumer.accept(error);
        }
    }

    public boolean isSuccess() {
        return value;
    }

    public boolean isFail() {
        return !value;
    }

    public <X extends Throwable> void orElseThrow(Function<E, ? extends X> exceptionProvider) throws X {
        if (!value) {
            throw exceptionProvider.apply(error);
        }
    }

    public interface SuccessHandler {
        void handle();
    }

}
