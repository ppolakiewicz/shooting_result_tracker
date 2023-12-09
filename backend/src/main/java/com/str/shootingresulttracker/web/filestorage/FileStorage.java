package com.str.shootingresulttracker.web.filestorage;

import java.util.Optional;

public interface FileStorage {

    StoredFile create();

    Optional<StoredFile> get(long id);

    void delete(long id);

}
