package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.*;
import com.tourismagency.tourism_agency.persistense.repository.ISaleRepository;
import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.service.exception.ApiError;
import com.tourismagency.tourism_agency.service.exception.TourismAgencyException;
import com.tourismagency.tourism_agency.service.interfaces.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleRepository saleRepository;

    private final ConversionService conversionService;

    @Override
    @Transactional(readOnly = true)
    public SaleDTO getSaleById(Long id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isEmpty()) {
            throw new TourismAgencyException(ApiError.CUSTOMER_NOT_FOUND);
        }else{
            return conversionService.convert(saleOptional.get(), SaleDTO.class);
        }
    }

    @Override
    @Transactional
    public void saveSale(SaleDTO saleDTO) {
        if(Objects.nonNull(saleDTO.id())){
            throw new TourismAgencyException(ApiError.SALE_WITH_SAME_ID);
        }
        Sale transformed = conversionService.convert(saleDTO, Sale.class);
        assert transformed != null;
        saleRepository.save(transformed);
    }

    @Override
    @Transactional
    public void updateSale(Long id, SaleDTO saleDTO) {
        if(saleRepository.findById(id).isPresent()) {
            throw new TourismAgencyException(ApiError.SALE_NOT_FOUND);
        }
        Sale transformed = conversionService.convert(saleDTO, Sale.class);
        assert transformed != null;
        transformed.setId(id);
        transformed.setPaymentMethod(conversionService.convert(saleDTO.paymentMethod(), PaymentMethodEntity.class));
        transformed.setTouristServicesList(saleDTO.touristServiceDTOList().stream()
                .map(customer -> conversionService.convert(saleDTO.touristServiceDTOList(), TouristService.class))
                .toList());
        transformed.setCustomer(conversionService.convert(saleDTO.customerDTO(), Customer.class));
        transformed.setTouristPackagesList(saleDTO.touristPackageDTOList().stream()
                .map(customer -> conversionService.convert(saleDTO.touristPackageDTOList(), TouristPackage.class))
                .toList());
        saleRepository.save(transformed);
    }

    @Override
    @Transactional
    public void deleteSale(Long id) {
        if(saleRepository.findById(id).isEmpty()) {
            throw new TourismAgencyException(ApiError.SALE_NOT_FOUND);
        }
        saleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDTO> getAllSales() {
        List<Sale> saleList = saleRepository.findAll();
        return saleList.stream()
                .map(sale -> conversionService.convert(sale, SaleDTO.class))
                .toList();
    }
}
