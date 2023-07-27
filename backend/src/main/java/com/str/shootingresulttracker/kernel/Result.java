package com.str.shootingresulttracker.kernel;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Result<E, T extends AbstractBaseDomainError> {

    private final T error;
    private final E value;

    public Result(T error) {
        Objects.requireNonNull(error, "Result can not have null error");
        this.error = error;
        this.value = null;
    }

    public Result(E value) {
        Objects.requireNonNull(value, "Result can not have null value");
        this.error = null;
        this.value = value;
    }

    public Optional<T> getError() {
        return Optional.ofNullable(error);
    }

    public Optional<E> getValue() {
        return Optional.ofNullable(value);
    }

    public <U> Result<U, T> mapValue(Function<? super E, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (getError().isPresent()) {
            return new Result<>(error);
        } else {
            return new Result<>(mapper.apply(value));
        }
    }

    public <X extends Throwable> E orElseThrow(Function<T, ? extends X> exceptionProvider) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionProvider.apply(error);
        }
    }

}
