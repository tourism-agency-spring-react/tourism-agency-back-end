package com.tourismagency.tourism_agency.presentation.dto;

import com.tourismagency.tourism_agency.persistense.model.PaymentMethodEntity;

public record PaymentMethodEntityDTO(Long id,
                                     PaymentMethodEntity paymentMethodEntity) {
}
