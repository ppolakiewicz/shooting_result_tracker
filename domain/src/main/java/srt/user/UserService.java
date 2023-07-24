package srt.user;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String email, String password) {

        if (userRepository.findUserByEmailIgnoreCase(email).isPresent()) {
            throw new UserAlreadyExistsException(email);
        }

        return userRepository.save(User.newBasicUser(email, password));
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmailIgnoreCase(email)
                .orElseThrow(() -> new UserDoNotExistsException(email));
    }
}
