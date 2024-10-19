package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalesDTOMapper extends Converter<List<SaleDTO>, List<Sale>> {

    @Override
    List<Sale> convert(List<SaleDTO> source);
}