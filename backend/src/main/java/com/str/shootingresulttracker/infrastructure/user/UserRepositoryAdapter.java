package com.str.shootingresulttracker.infrastructure.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import srt.user.User;
import srt.user.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
class UserRepositoryAdapter implements UserRepository {

    private final UserMapper mapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findUserById(UUID id) {
        return userJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findUserByEmailIgnoreCase(String email) {
        return userJpaRepository.findByEmailIgnoreCase(email).map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(userJpaRepository.save(mapper.toEntity(user)));
    }
}
