package srt.user;

import lombok.AllArgsConstructor;
import srt.kernel.Result;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Result<UserAlreadyExistsError, User> create(String email, String password) {

        if (userRepository.findUserByEmailIgnoreCase(email).isPresent()) {
            return new Result<>(new UserAlreadyExistsError(email));
        }

        return new Result<>(userRepository.save(User.newBasicUser(email, password)));
    }

    public Result<UserDoNotExistsError, User> findByEmail(String email) {
        return userRepository.findUserByEmailIgnoreCase(email)
                .<Result<UserDoNotExistsError, User>>map(Result::new)
                .orElseGet(() -> new Result<>(new UserDoNotExistsError(email)));
    }

}
