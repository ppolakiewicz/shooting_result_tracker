package srt.user;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String email, String password) {

        if (userRepository.findUserByEmailIgnoreCase(email).isPresent()) {
            //TODO: throw exception
        }

        return userRepository.save(User.newBasicUser(email, password));
    }

    public User findByEmail(String email){
        //TODO: Add domain exception
        return userRepository.findUserByEmailIgnoreCase(email)
                .orElseThrow();
    }
}
