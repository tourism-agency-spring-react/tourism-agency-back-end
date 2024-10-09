package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;

import java.util.List;

public interface ITouristService {

    public TouristServiceDTO getById (Long id);

    public void save (TouristServiceDTO touristServiceDTO);

    public void update (Long id, TouristServiceDTO touristServiceDTO);

    public void delete (Long id);

    public List<TouristServiceDTO> getAll();

}
