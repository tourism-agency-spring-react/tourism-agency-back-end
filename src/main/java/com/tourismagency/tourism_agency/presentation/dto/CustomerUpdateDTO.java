package com.tourismagency.tourism_agency.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerUpdateDTO(Long id,
                                @NotBlank(message = "The name can´t be null or empty")
                                @Size(min = 2, max = 50, message = "Enter a minimum of 2 characters and a maximum of 50")
                                String firstName,

                                @NotBlank(message = "The lastName can´t be null or empty")
                                @Size(min = 2, max = 50, message = "Enter a minimum of 2 characters and a maximum of 50")
                                String lastName,

                                @NotBlank(message = "The dni can´t be null or empty")
                                @Size(min = 7, max = 12, message = "Enter a minimum of 7 characters and a maximum of 12")
                                String dni,

                                @NotBlank(message = "The phone number can´t be null or empty")
                                @Size(min = 7, max = 20, message = "Enter a minimum of 2 characters and a maximum of 20")
                                String phoneNumber,

                                @NotBlank(message = "The direction can´t be null or empty")
                                @Size(min = 7, max = 120, message = "Enter a minimum of 7 characters and a maximum of 120")
                                String direction,

                                @Past(message = "Enter the correct date of your birthday")
                                LocalDate birthDate,

                                @NotBlank(message = "The field is required")
                                @Email(message = "The email haven't a valid format")
                                String email,

                                @Size(min = 8, message = "The field must have a minimum of 8 letters")
                                @NotBlank(message = "The field is required")
                                String password,

                                @NotNull(message = "The field is required")
                                Long userId) {
}
