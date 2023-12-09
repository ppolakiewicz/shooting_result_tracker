package com.str.shootingresulttracker.web.filestorage;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public interface StoredFile {

    InputStream getInputStream() throws SQLException;

    OutputStream getOutputStream();

    long getId();

}
