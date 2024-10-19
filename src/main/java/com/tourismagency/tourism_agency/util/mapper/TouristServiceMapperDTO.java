package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface TouristServiceMapperDTO extends Converter<TouristServiceDTO, TouristService> {

    @Override
    TouristService convert(TouristServiceDTO resource);

}

