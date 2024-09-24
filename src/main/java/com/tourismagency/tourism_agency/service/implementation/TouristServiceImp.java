package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.persistense.repository.ITouristServiceRepository;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
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
    public TouristService
    getById (Long id) {
        return touristServiceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save (TouristServiceDTO touristServiceDTO) {
        touristServiceRepository.save(touristServiceDTO);
    }

    @Override
    @Transactional
    public void update (Long id, TouristServiceDTO touristServiceDTO) {
        TouristService touristServiceGet = this.getById(id);

        touristServiceGet.setName(touristServiceDTO.name());
        touristServiceGet.setDescription(touristServiceDTO.description());
        touristServiceGet.setDestiny(touristServiceDTO.destiny());
        touristServiceGet.setDate(touristServiceDTO.date());
        touristServiceGet.setPrice(touristServiceDTO.price());
        touristServiceGet.setServiceType(touristServiceDTO.serviceType());

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
