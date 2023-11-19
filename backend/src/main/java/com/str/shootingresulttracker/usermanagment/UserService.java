package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.domain.kernel.DomainResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
@AllArgsConstructor
public class UserService {

    private final UserJpaRepository repository;
    private final UserMapper mapper;
    private final Clock clock;

    public DomainResult<UserDto> create(String username, String password) {

        if (repository.findByUsername(username).isPresent()) {
            return new DomainResult<>(new UserAlreadyExistsError(username));
        }

        var newUser = mapper.toDto(repository.save(UserEntity.newBasicUser(username, password, clock)));
        return new DomainResult<>(newUser);
    }

    public DomainResult<UserDto> findByUserName(String email) {
        return repository.findByUsername(email)
                .map(mapper::toDto)
                .map(DomainResult::new)
                .orElseGet(() -> new DomainResult<>(new UserDoNotExistsError(email)));
    }

}
