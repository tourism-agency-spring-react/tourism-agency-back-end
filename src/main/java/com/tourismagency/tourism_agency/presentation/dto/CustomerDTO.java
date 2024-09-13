package com.tourismagency.tourism_agency.presentation.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record CustomerDTO(Long id,
                          String firstName,
                          String lastName,
                          String dni,
                          String phoneNumber,
                          String direction,
                          LocalDate birthDate) implements Serializable {
}
