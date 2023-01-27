package com.str.shootingresulttracker.adapter.api.auth;

import lombok.Builder;

@Builder
record AuthenticationResponseDto(
        String token
) {
}
