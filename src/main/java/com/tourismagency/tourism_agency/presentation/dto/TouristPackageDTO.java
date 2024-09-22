package com.tourismagency.tourism_agency.presentation.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TouristPackageDTO(Long id,
                                Double price,
                                List<TouristServiceDTO> touristServicesList,
                                List<SaleDTO> salesList){
}
