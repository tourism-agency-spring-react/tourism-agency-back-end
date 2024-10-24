package com.tourismagency.tourism_agency.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginRequestDTO(
        @NotBlank(message = "El campo es requerido.")
        @Email(message = "El correo no es valido.")
        String email,
        @Size(min = 8, message = "El campo debe tener minimo 8 caracteres.")
        @NotBlank(message = "El campo es requerido.")
        String password
) {
}
