package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TouristServicesMapper extends Converter<List<TouristService>, List<TouristServiceDTO>> {

    @Override
    List<TouristServiceDTO> convert(List<TouristService> source);
}
