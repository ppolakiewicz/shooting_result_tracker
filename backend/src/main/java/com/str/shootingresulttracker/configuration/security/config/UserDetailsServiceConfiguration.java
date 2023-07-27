package com.str.shootingresulttracker.configuration.security.config;

import com.str.shootingresulttracker.usermanagment.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@AllArgsConstructor
class UserDetailsServiceConfiguration {

    private final UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userService.findByUserName(username)
                .orElseThrow(userDoNotExistsError -> new UsernameNotFoundException("User" + username + "do not exists"));
    }
}

