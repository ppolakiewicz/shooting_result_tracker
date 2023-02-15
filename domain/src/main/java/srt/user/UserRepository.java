package srt.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUserById(UUID id);

    Optional<User> findUserByEmailIgnoreCase(String email);

    User save(User user);
}
