package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.persistense.repository.ITouristServiceRepository;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
import com.tourismagency.tourism_agency.util.mapper.TouristServiceMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TouristServiceService implements ITouristService {

    private final ITouristServiceRepository touristServiceRepository;

    @Override
    @Transactional(readOnly = true)
    public TouristServiceDTO getById (Long id) {
        TouristService touristService = touristServiceRepository.findById(id).orElse(null);

        if (touristService == null) {
            throw new ResourceNotFoundException("tourist service: ", "id", id);
        }else{
            return TouristServiceMapper.touristServiceToTouristServiceDTO(touristService);
        }
    }

    @Override
    @Transactional
    public void save (TouristServiceDTO touristServiceDTO) {
        TouristService touristService = TouristServiceMapper.touristServiceDTOToTouristService(touristServiceDTO);

        Optional<TouristService> touristServiceOptional = touristServiceRepository.findById(touristService.getId());

        if(touristServiceOptional.isPresent()){
            throw new IllegalArgumentException("The ID entered already exists in the data base");
        }else{
            touristServiceRepository.save(touristService);
        }
    }

    @Override
    @Transactional
    public void update (Long id, TouristServiceDTO touristServiceDTO) {
        Optional<TouristService> touristServiceOptional = touristServiceRepository.findById(id);

        if(touristServiceOptional.isPresent()){
            touristServiceOptional.get().setName(touristServiceDTO.name());
            touristServiceOptional.get().setDescription(touristServiceDTO.description());
            touristServiceOptional.get().setDestiny(touristServiceDTO.destiny());
            touristServiceOptional.get().setDate(touristServiceDTO.date());
            touristServiceOptional.get().setPrice(touristServiceDTO.price());
            touristServiceOptional.get().setServiceType(touristServiceDTO.serviceType());

            touristServiceRepository.save(touristServiceOptional.get());
        }else{
            throw new IllegalArgumentException("The ID entered already exists in the data base");
        }
    }

    @Override
    @Transactional
    public void delete (Long id) {
        Optional<TouristService> touristServiceOptional = touristServiceRepository.findById(id);

        if(touristServiceOptional.isPresent()) {
            touristServiceRepository.delete(touristServiceOptional.get());
        }else{
            throw new EntityNotFoundException("Tourist Service not found.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TouristServiceDTO> getAll() {
        List<TouristService> touristServiceList = touristServiceRepository.findAll();

        if (touristServiceList.isEmpty()) {
            throw new EntityNotFoundException("List is empty.");
        }else{
            return touristServiceList.stream()
                    .map(TouristServiceMapper::touristServiceToTouristServiceDTO)
                    .toList();
        }
    }
}
