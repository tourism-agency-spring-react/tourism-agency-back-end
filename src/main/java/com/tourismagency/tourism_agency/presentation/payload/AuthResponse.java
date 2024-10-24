package com.tourismagency.tourism_agency.presentation.payload;

import lombok.Builder;

import java.util.Set;

@Builder
public record AuthResponse(
        String token,
        String message,
        String email,
        Set<String> roles
) {
}
