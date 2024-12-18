package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.persistense.model.Customer;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends Converter<Customer, CustomerDTO> {

    @Override
    CustomerDTO convert(Customer source);
}
