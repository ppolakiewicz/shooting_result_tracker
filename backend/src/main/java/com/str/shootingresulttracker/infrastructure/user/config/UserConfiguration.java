package com.str.shootingresulttracker.infrastructure.user.config;

import com.str.shootingresulttracker.domain.user.UserRepository;
import com.str.shootingresulttracker.domain.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
