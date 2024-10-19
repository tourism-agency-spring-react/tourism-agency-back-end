package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TouristPackagesMapper extends Converter<List<TouristPackage>, List<TouristPackageDTO>> {

    @Override
    List<TouristPackageDTO> convert(List<TouristPackage> source);
}