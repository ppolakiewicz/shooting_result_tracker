package com.str.shootingresulttracker.adapter.api.auth;

import lombok.Builder;

@Builder
record RegisterDto(
        String email,
        String password
) {
}
