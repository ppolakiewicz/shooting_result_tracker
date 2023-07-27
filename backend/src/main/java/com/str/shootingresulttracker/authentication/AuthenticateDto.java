package com.str.shootingresulttracker.authentication;

import lombok.Builder;

@Builder
record AuthenticateDto(
        String email,
        String password
) {
}
