package srt.kernel;

import java.util.Objects;

public class Result<T extends AbstractBaseDomainError, E> {

    private final T error;
    private final E value;

    public Result(T error) {
        Objects.requireNonNull(error, "Result can not have null error");
        this.error = error;
        this.value = null;
    }

    public Result(E value){
        Objects.requireNonNull(value, "Result can not have null value");
        this.error = null;
        this.value = value;
    }

    public boolean containsValue(){
        return value != null;
    }

    public boolean containsError(){
        return error != null;
    }

    public T getError() {
        return error;
    }

    public E getValue() {
        return value;
    }
}
