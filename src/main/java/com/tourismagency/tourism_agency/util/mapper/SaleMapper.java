package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.persistense.model.Sale;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface SaleMapper  extends Converter<Sale, SaleDTO> {

    @Override
    SaleDTO convert(Sale source);

}
