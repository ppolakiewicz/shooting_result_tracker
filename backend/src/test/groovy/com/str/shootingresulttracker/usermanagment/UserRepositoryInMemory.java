package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.kernel.AbstractInMemoryRepository;

import java.util.Optional;

class UserRepositoryInMemory extends AbstractInMemoryRepository<UserEntity> implements UserJpaRepository {

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return collection.values().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

}
