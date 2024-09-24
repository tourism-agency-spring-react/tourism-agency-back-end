package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;

import java.util.List;

public interface ITouristService {

    public TouristService getById (Long id);

    public void save (TouristServiceDTO touristServiceDTO);

    public void update (Long id, TouristService touristService);

    public void delete (Long id);

    public List<TouristService> getAll();

}
