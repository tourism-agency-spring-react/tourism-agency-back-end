package com.tourismagency.tourism_agency.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record TouristPackageDTO(Long id,

                                @NotNull(message = "The price can't be null")
                                @Positive(message = "The price must be greater than zero")
                                @Digits(integer = 6, fraction = 2, message = "The price must have up to 6 integer digits and 2 decimal digits")
                                Double price,

                                @NotEmpty(message = "The list can't be empty")
                                @Valid
                                List<TouristServiceDTO> touristServicesList,

                                @NotEmpty(message = "The sales list can't be empty")
                                @Valid
                                List<SaleDTO> salesList){
}
