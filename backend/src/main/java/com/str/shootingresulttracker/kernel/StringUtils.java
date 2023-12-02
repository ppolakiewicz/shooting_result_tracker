package com.str.shootingresulttracker.kernel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static void requiredNonEmpty(String value, String fieldName) {
        if (!org.springframework.util.StringUtils.hasText(value)) {
            throw new IllegalArgumentException(fieldName + " can not be empty");
        }
    }
}
