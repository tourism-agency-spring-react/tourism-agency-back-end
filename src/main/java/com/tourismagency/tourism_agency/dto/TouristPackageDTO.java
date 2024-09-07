package com.tourismagency.tourism_agency.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record TouristPackageDTO(Long id,
                                Double price,
                                List<TouristServiceDTO> touristServicesList,
                                List<SaleDTO> salesList) implements Serializable {
}
