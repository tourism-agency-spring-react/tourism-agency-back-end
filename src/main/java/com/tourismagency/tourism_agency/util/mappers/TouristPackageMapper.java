package com.tourismagency.tourism_agency.util.mappers;

import com.tourismagency.tourism_agency.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.model.TouristPackage;

import java.util.stream.Collectors;

public class TouristPackageMapper {
    public static TouristPackageDTO touristPackageToTouristPackageDTO(TouristPackage touristPackage) {
        return TouristPackageDTO.builder()
                .id(touristPackage.getId())
                .price(touristPackage.getPrice())
                .touristServicesList(touristPackage.getTouristServicesList().stream()
                        .map(TouristServiceMapper::touristServiceToTouristServiceDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static TouristPackage touristPackageDTOToTouristPackage(TouristPackageDTO touristPackageDTO) {
        return TouristPackage.builder()
                .id(touristPackageDTO.id())
                .price(touristPackageDTO.price())
                .touristServicesList(touristPackageDTO.touristServicesList().stream()
                        .map(TouristServiceMapper::touristServiceDTOToTouristService)
                        .collect(Collectors.toList()))
                .build();
    }
}
