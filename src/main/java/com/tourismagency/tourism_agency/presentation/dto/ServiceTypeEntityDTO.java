package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.ServiceTypeEnum;
import lombok.Builder;

@Builder
public record ServiceTypeEntityDTO(Long id,
                                   ServiceTypeEnum serviceTypeName) {
}
