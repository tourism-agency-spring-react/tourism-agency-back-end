package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.CountryEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;


import java.time.LocalDate;


@Builder
public record TouristServiceDTO(Long id,
                                @NotBlank(message = "The name can't be null or empty")
                                @Size(min = 2, max = 50, message = "Enter a minimum of 2 characters and a maximum of 50")
                                String name,

                                @NotBlank(message = "The description can't be null or empty")
                                @Size(min = 10, max = 500, message = "Enter a minimum of 10 characters and a maximum of 500")
                                String description,

                                @Valid
                                CountryEnum destiny,

                                @NotNull
                                @Future(message = "The date must be in the future")
                                LocalDate date,

                                @NotNull(message = "The price can't be null")
                                @Positive(message = "The price must be greater than zero")
                                @Digits(integer = 6, fraction = 2, message = "The price must have up to 6 integer digits and 2 decimal digits")
                                Double price,

                                @Valid
                                ServiceTypeEntityDTO serviceTypeEntityDTO){
}
