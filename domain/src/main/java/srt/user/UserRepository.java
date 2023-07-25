package srt.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByEmailIgnoreCase(String email);

    User save(User user);
}
