package com.str.shootingresulttracker.kernel

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
@SpringBootTest
abstract class AbstractIntegrationTest extends Specification {

    private static final DockerImageName POSTGRES_DOCKER_IMAGE = DockerImageName.parse('postgres:15.1-alpine3.17')
    private static final GenericContainer POSTGRES_CONTAINER = new PostgreSQLContainer(POSTGRES_DOCKER_IMAGE)
            .withReuse(true)

    @DynamicPropertySource
    static void registerPostgresProperties(DynamicPropertyRegistry registry){
        POSTGRES_CONTAINER.start()
        registry.add('spring.datasource.url', () -> POSTGRES_CONTAINER.getJdbcUrl())
        registry.add('spring.datasource.username', () -> POSTGRES_CONTAINER.getUsername())
        registry.add('spring.datasource.password', () -> POSTGRES_CONTAINER.getPassword())
    }

}
