package com.str.shootingresulttracker.authentication;

import lombok.Builder;

@Builder
record RegisterDto(
        String email,
        String password
) {
}
