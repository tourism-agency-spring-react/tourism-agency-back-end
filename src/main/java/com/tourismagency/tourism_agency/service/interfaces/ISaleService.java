package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;

import java.util.List;

public interface ISaleService {

    public SaleDTO getSaleById(Long id);

    public void saveSale(SaleDTO saleDTO);

    public void updateSale(Long id, SaleDTO saleDTO);

    public void deleteSale(Long id);

    public List<SaleDTO> getAllSales();

}
