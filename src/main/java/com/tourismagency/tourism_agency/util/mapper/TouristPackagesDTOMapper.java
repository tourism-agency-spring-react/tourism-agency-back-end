package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TouristPackagesDTOMapper extends Converter<List<TouristPackageDTO>, List<TouristPackage>> {

    @Override
    List<TouristPackage> convert(List<TouristPackageDTO> source);
}
