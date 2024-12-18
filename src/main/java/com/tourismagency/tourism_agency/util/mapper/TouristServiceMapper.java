package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.persistense.model.TouristService;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface TouristServiceMapper extends Converter<TouristService, TouristServiceDTO> {

    @Override
    TouristServiceDTO convert(TouristService resource);

}
