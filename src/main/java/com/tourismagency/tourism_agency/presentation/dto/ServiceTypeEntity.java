package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.ServiceTypeEnum;

public record ServiceTypeEntity(Long id,
                                ServiceTypeEnum serviceTypeName) {
}
