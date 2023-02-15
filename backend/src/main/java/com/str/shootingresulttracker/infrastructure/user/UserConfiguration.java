package com.str.shootingresulttracker.infrastructure.user;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import srt.user.UserRepository;
import srt.user.UserService;

@Configuration
class UserConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
