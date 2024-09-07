package com.tourismagency.tourism_agency.util.mappers;

import com.tourismagency.tourism_agency.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.model.TouristService;

public class TouristServiceMapper {

    public static TouristServiceDTO touristServiceToTouristServiceDTO(TouristService touristService) {
        return TouristServiceDTO.builder()
                .id(touristService.getId())
                .name(touristService.getName())
                .description(touristService.getDescription())
                .destiny(touristService.getDestiny())
                .date(touristService.getDate())
                .price(touristService.getPrice())
                .serviceType(touristService.getServiceType())
                .build();
    }

    public static TouristService touristServiceDTOToTouristService(TouristServiceDTO touristServiceDTO) {
        return TouristService.builder()
                .id(touristServiceDTO.id())
                .name(touristServiceDTO.name())
                .description(touristServiceDTO.description())
                .destiny(touristServiceDTO.destiny())
                .date(touristServiceDTO.date())
                .price(touristServiceDTO.price())
                .serviceType(touristServiceDTO.serviceType())
                .build();
    }

}
