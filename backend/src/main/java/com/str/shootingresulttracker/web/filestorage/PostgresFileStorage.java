package com.str.shootingresulttracker.web.filestorage;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
class PostgresFileStorage implements FileStorage {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public StoredFile create() {
        try {
            return entityManager.unwrap(Session.class).doReturningWork(connection -> {
                var largeObjectManager = connection.unwrap(org.postgresql.PGConnection.class).getLargeObjectAPI();
                return new PostgresLargeObject(largeObjectManager.open(largeObjectManager.createLO()));
            });
        } catch (HibernateError e) {
            log.error("Error during large object creation", e);
            throw new FileStorageException(e);
        }
    }

    @Override
    public Optional<StoredFile> get(long oid) {
        if (largeObjectDoNotExists(oid)) {
            return Optional.empty();
        }

        return entityManager.unwrap(Session.class).doReturningWork(connection -> {
            try {
                connection.setAutoCommit(false);
                var largeObjectManager = connection.unwrap(org.postgresql.PGConnection.class).getLargeObjectAPI();
                return Optional.of(new PostgresLargeObject(largeObjectManager.open(oid)));
            } catch (SQLException e) {
                log.warn("Error during reading large object file");
                return Optional.empty();
            }
        });
    }

    @Override
    public void delete(long oid) {
        if (largeObjectDoNotExists(oid)) {
            return;
        }

        try {
            entityManager.unwrap(Session.class).doWork(connection -> {
                var largeObjectManager = connection.unwrap(org.postgresql.PGConnection.class).getLargeObjectAPI();
                largeObjectManager.delete(oid);
            });
        } catch (HibernateException e) {
            log.error("Error during deleting large object file");
            throw new FileStorageException(e);
        }
    }

    private boolean largeObjectDoNotExists(long oid) {
        var query = """
                select case when count(*) = 0 then true else false end as exists
                from pg_largeobject_metadata
                where oid = ?
                """;
        return jdbcTemplate.query(query,
                (rs, rowNum) -> rs.getBoolean("exists"),
                oid
        ).get(0);
    }
}
