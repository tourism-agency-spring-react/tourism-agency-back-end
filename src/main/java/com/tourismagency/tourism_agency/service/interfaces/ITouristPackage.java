package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;

import java.util.List;

public interface ITouristPackage {

    public TouristPackage getById (Long id);

    public void save (TouristPackage touristPackage);

    public void update (Long id, TouristPackage touristPackage);

    public void delete (Long id);

    public List<TouristPackage> getAll();



}
