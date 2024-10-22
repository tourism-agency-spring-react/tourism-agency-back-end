package com.tourismagency.tourism_agency.presentation.dto;

import lombok.Builder;

@Builder
public record UserDTO(
        Long id,
        String email
) {
}
