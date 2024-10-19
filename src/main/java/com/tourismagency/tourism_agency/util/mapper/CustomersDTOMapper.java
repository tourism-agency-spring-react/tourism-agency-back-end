package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomersDTOMapper extends Converter<List<CustomerDTO>, List<Customer>> {

    @Override
    List<Customer> convert(List<CustomerDTO> source);
}
