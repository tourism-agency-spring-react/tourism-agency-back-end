package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import com.tourismagency.tourism_agency.persistense.repository.ISaleRepository;
import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import com.tourismagency.tourism_agency.service.interfaces.ISaleService;
import com.tourismagency.tourism_agency.util.mapper.SaleMapper;
import com.tourismagency.tourism_agency.util.mapper.TouristPackageMapper;
import com.tourismagency.tourism_agency.util.mapper.TouristServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleRepository saleRepository;

    @Override
    @Transactional(readOnly = true)
    public SaleDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", "id", id));
        return SaleMapper.saleToSaleDTO(sale);
    }

    @Override
    @Transactional
    public void saveSale(SaleDTO saleDTO) {
        Sale sale = SaleMapper.saleDTOToSale(saleDTO);

        if (sale.getId() != null && saleRepository.existsById(sale.getId())) {
            throw new IllegalArgumentException("Sale ID already exists in the database");
        }
        saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void updateSale(Long id, SaleDTO saleDTO) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", "id", id));

        if (!saleDTO.touristPackageDTOList().isEmpty()) {
            sale.setTouristPackagesList(saleDTO.touristPackageDTOList().stream()
                    .map(TouristPackageMapper::touristPackageDTOToTouristPackage)
                    .collect(Collectors.toList()));
        }

        if (!saleDTO.touristServiceDTOList().isEmpty()) {
            sale.setTouristServicesList(saleDTO.touristServiceDTOList().stream()
                    .map(TouristServiceMapper::touristServiceDTOToTouristService)
                    .collect(Collectors.toList()));
        }

        sale.setPaymentMethod(saleDTO.paymentMethod());
        saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", "id", id));
        saleRepository.delete(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDTO> getAllSales() {
        List<Sale> saleList = saleRepository.findAll();
        return saleList.stream()
                .map(SaleMapper::saleToSaleDTO)
                .toList();
    }
}
