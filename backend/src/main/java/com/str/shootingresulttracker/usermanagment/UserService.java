package com.str.shootingresulttracker.usermanagment;

import com.str.shootingresulttracker.domain.kernel.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserJpaRepository repository;
    private final UserMapper mapper;

    public Result<UserDto, UserAlreadyExistsError> create(String username, String password) {

        if (repository.findByUsername(username).isPresent()) {
            return new Result<>(new UserAlreadyExistsError(username));
        }

        var newUser = mapper.toDto(repository.save(UserEntity.newBasicUser(username, password)));
        return new Result<>(newUser);
    }

    public Result<UserDto, UserDoNotExistsError> findByUserName(String email) {
        return repository.findByUsername(email)
                .map(mapper::toDto)
                .<Result<UserDto, UserDoNotExistsError>>map(Result::new)
                .orElseGet(() -> new Result<>(new UserDoNotExistsError(email)));
    }

}
