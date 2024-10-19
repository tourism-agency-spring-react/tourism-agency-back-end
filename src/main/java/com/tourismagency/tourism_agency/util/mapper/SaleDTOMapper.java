package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface SaleDTOMapper extends Converter<SaleDTO, Sale> {
    @Override
    Sale convert(SaleDTO source);
}
