package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.PaymentMethodEntity;
import com.tourismagency.tourism_agency.presentation.dto.PaymentMethodEntityDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PaymentMethodEntityDTOMapper extends Converter<PaymentMethodEntityDTO, PaymentMethodEntity> {

    @Override
    PaymentMethodEntity convert(PaymentMethodEntityDTO resource);
}