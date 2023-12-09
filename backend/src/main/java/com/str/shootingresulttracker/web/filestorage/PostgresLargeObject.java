package com.str.shootingresulttracker.web.filestorage;

import lombok.SneakyThrows;
import org.postgresql.largeobject.LargeObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

class PostgresLargeObject implements StoredFile {

    private final LargeObject largeObject;

    public PostgresLargeObject(LargeObject largeObject) {
        Objects.requireNonNull(largeObject, "Large object can not be null");
        this.largeObject = largeObject;
    }

    @SneakyThrows
    @Override
    public InputStream getInputStream() {
        return largeObject.getInputStream();
    }

    @SneakyThrows
    @Override
    public OutputStream getOutputStream() {
        return largeObject.getOutputStream();
    }

    @Override
    public long getId() {
        return largeObject.getLongOID();
    }
}
