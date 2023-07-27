package com.str.shootingresulttracker.authentication;

import lombok.Builder;

@Builder
record AuthenticationResponseDto(
        String token
) {
}
