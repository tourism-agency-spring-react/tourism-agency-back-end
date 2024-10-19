package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.persistense.model.PaymentMethodEntity;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.presentation.dto.PaymentMethodEntityDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PaymentMethodEntityMapper extends Converter<PaymentMethodEntity, PaymentMethodEntityDTO> {

    @Override
    PaymentMethodEntityDTO convert(PaymentMethodEntity resource);
}
