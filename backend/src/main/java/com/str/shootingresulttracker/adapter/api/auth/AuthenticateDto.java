package com.str.shootingresulttracker.adapter.api.auth;

import lombok.Builder;

@Builder
record AuthenticateDto(
        String email,
        String password
) {
}
