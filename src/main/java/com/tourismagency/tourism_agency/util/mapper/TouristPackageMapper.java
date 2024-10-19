package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TouristPackageMapper extends Converter<TouristPackage, TouristPackageDTO> {

    @Override
    TouristPackageDTO convert(TouristPackage resource);
}
