package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;

import java.util.List;

public interface ITouristPackage {

    public TouristPackageDTO getById (Long id);

    public void save (TouristPackageDTO touristPackageDTO);

    public void update (Long id, TouristPackageDTO touristPackageDTO);

    public void delete (Long id);

    public List<TouristPackageDTO> getAll();



}
