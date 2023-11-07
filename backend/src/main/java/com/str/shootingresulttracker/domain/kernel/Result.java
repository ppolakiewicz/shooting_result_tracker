package com.str.shootingresulttracker.domain.kernel;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<V, E extends AbstractBaseDomainError> {

    private final V value;
    private final E error;

    public Result(E error) {
        Objects.requireNonNull(error, "Result can not have null error");
        this.error = error;
        this.value = null;
    }

    public Result(V value) {
        Objects.requireNonNull(value, "Result can not have null value");
        this.error = null;
        this.value = value;
    }

    protected Result(V value, E error) {
        this.value = value;
        this.error = error;
    }

    public Optional<E> getError() {
        return Optional.ofNullable(error);
    }

    public Optional<V> getValue() {
        return Optional.ofNullable(value);
    }

    public <U> Result<U, E> mapValue(Function<? super V, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (getError().isPresent()) {
            return new Result<>(error);
        } else {
            return new Result<>(mapper.apply(value));
        }
    }

    public <X extends Throwable> V orElseThrow(Function<E, ? extends X> exceptionProvider) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionProvider.apply(error);
        }
    }

    public void ifError(Consumer<? super E> errorConsumer) {
        getError().ifPresent(errorConsumer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result<?, ?> result = (Result<?, ?>) o;

        if (!Objects.equals(value, result.value)) return false;
        return Objects.equals(error, result.error);
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }

}
