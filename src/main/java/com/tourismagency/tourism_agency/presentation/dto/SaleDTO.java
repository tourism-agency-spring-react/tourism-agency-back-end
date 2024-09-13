package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.PaymentMethodEnum;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
public record SaleDTO(Long saleId,
                      CustomerDTO customerDTO,
                      LocalDate createAt,
                      List<TouristServiceDTO> touristServiceDTOList,
                      List<TouristPackageDTO> touristPackageDTOList,
                      PaymentMethodEnum paymentMethod) implements Serializable {
}
