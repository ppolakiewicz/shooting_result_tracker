package srt.kernel;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
abstract public class AbstractInMemoryRepository<T> {

    protected final Map<UUID, T> collection;

    public AbstractInMemoryRepository() {
        collection = new ConcurrentHashMap<>();
    }

    public T save(T value) {
        collection.put(getId(value), value);
        return value;
    }

    protected UUID getId(T object) {
        try {
            var idField = object.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return UUID.fromString(idField.get(object).toString());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            log.error("Could not read object ID");
            throw new RuntimeException(e);
        }
    }
}
