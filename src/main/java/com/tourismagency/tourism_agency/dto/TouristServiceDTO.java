package com.tourismagency.tourism_agency.dto;

import com.tourismagency.tourism_agency.enums.CountryEnum;
import com.tourismagency.tourism_agency.enums.ServiceTypeEnum;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record TouristServiceDTO(Long id,
                                String name,
                                String description,
                                CountryEnum destiny,
                                LocalDate date,
                                Double price,
                                ServiceTypeEnum serviceType) implements Serializable {
}
