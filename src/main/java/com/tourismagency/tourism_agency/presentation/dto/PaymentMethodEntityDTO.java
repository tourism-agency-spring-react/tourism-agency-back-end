package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.enums.PaymentMethodEnum;

public record PaymentMethodEntityDTO(Long id,
                                     PaymentMethodEnum paymentMethod) {
}
