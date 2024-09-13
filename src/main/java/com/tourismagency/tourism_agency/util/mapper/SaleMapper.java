package com.tourismagency.tourism_agency.util.mapper;

import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.persistense.model.Sale;

import java.util.stream.Collectors;

public class SaleMapper {
    public static SaleDTO saleToSaleDTO(Sale sale) {
        return SaleDTO.builder()
                .saleId(sale.getId())
                .customerDTO(CustomerMapper.customerToCustomerDto(sale.getCustomer()))
                .touristServiceDTOList(sale.getTouristServicesList().stream()
                        .map(TouristServiceMapper::touristServiceToTouristServiceDTO)
                        .collect(Collectors.toList()))
                .touristPackageDTOList(sale.getTouristPackagesList().stream()
                        .map(TouristPackageMapper::touristPackageToTouristPackageDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Sale saleDTOToSale(SaleDTO saleDTO) {
        return Sale.builder()
                .id(saleDTO.saleId())
                .customer(CustomerMapper.customerDTOToCustomer(saleDTO.customerDTO()))
                .touristServicesList(saleDTO.touristServiceDTOList().stream()
                        .map(TouristServiceMapper::touristServiceDTOToTouristService)
                        .collect(Collectors.toList()))
                .touristPackagesList(saleDTO.touristPackageDTOList().stream()
                        .map(TouristPackageMapper::touristPackageDTOToTouristPackage)
                        .collect(Collectors.toList()))
                .build();
    }
}
