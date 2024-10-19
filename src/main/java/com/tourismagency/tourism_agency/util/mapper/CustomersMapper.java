package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomersMapper extends Converter<List<Customer>, List<CustomerDTO>> {

        @Override
        List<CustomerDTO> convert(List<Customer> source);
}
