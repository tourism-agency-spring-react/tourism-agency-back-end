package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;

public record RoleEntityDTO(

        Long id,
        @NotBlank
        RoleEnum role) {
}
