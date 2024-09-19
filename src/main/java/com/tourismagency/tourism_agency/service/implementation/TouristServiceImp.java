package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.persistense.repository.ITouristServiceRepository;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor

public class TouristServiceImp implements ITouristService {

    private final ITouristServiceRepository touristServiceRepository;

    @Override
    public TouristService getById (Long id) {
        return touristServiceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save (TouristService touristService) {
        touristServiceRepository.save(touristService);
    }

    @Override
    @Transactional
    public void update (Long id, TouristService touristService) {
        TouristService touristServiceGet = this.getById(id);

        touristServiceGet.setName(touristService.getName());
        touristServiceGet.setDescription(touristService.getDescription());
        touristServiceGet.setDestiny(touristService.getDestiny());
        touristServiceGet.setDate(touristService.getDate());
        touristServiceGet.setPrice(touristService.getPrice());
        touristServiceGet.setServiceType(touristService.getServiceType());

        touristServiceRepository.save(touristServiceGet);

    }

    @Override
    @Transactional
    public void delete (Long id) {
        touristServiceRepository.deleteById(id);
    }

    @Override
    public List<TouristService> getAll() {
        return touristServiceRepository.findAll();
    }

}
