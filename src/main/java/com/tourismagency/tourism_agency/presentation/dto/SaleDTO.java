package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.PaymentMethodEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;


import java.time.LocalDate;
import java.util.List;

@Builder
public record SaleDTO(Long saleId,
                      @Valid
                      CustomerDTO customerDTO,

                      @NotNull(message = "The date can't be null")
                      @PastOrPresent(message = "The creation date can't be in the future")
                      LocalDate createAt,

                      @NotEmpty(message = "The tourist service list can't be empty")
                      @Valid
                      List<TouristServiceDTO> touristServiceDTOList,

                      @NotEmpty(message = "The tourist package list can't be empty")
                      @Valid
                      List<TouristPackageDTO> touristPackageDTOList,

                      @Valid
                      PaymentMethodEnum paymentMethod){
}
