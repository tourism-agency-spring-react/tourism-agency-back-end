package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.CountryEnum;

public record CountryEntityDTO(Long id,
                               CountryEnum country) {
}
