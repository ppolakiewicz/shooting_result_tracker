package srt.user;

import srt.kernel.AbstractInMemoryRepository;

import java.util.Optional;

class UserRepositoryInMemory extends AbstractInMemoryRepository<User> implements UserRepository {

    @Override
    public Optional<User> findUserByEmailIgnoreCase(String email) {
        return collection.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
