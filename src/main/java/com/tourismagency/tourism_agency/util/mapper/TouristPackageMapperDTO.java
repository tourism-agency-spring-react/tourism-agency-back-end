package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface TouristPackageMapperDTO extends Converter<TouristPackageDTO, TouristPackage> {

    @Override
    TouristPackage convert(TouristPackageDTO resource);
}