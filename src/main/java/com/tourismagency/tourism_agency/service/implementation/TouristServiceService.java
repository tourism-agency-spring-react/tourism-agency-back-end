package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import com.tourismagency.tourism_agency.persistense.model.ServiceTypeEntity;
import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.persistense.repository.ITouristServiceRepository;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.service.exception.ApiError;
import com.tourismagency.tourism_agency.service.exception.TourismAgencyException;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TouristServiceService implements ITouristService {

    private final ITouristServiceRepository touristServiceRepository;

    private final ConversionService conversionService;

    @Override
    @Transactional(readOnly = true)
    public TouristServiceDTO getById (Long id) {
        Optional<TouristService> touristServiceOptional = touristServiceRepository.findById(id);
        if (touristServiceOptional.isEmpty()) {
            throw new TourismAgencyException(ApiError.TOURIST_SERVICE_NOT_FOUND);
        }else{
            return conversionService.convert(touristServiceOptional.get(), TouristServiceDTO.class);
        }
    }

    @Override
    @Transactional
    public void save (TouristServiceDTO touristServiceDTO) {
        if(Objects.nonNull(touristServiceDTO.id())){
            throw new TourismAgencyException(ApiError.TOURIST_SERVICE_NOT_FOUND);
        }
        TouristService transformed = conversionService.convert(touristServiceDTO, TouristService.class);
        assert transformed != null;
        touristServiceRepository.save(transformed);
    }

    @Override
    @Transactional
    public void update (Long id, TouristServiceDTO touristServiceDTO) {
        if(touristServiceRepository.findById(id).isPresent()) {
            throw new TourismAgencyException(ApiError.TOURIST_SERVICE_NOT_FOUND);
        }
        TouristService transformed = conversionService.convert(touristServiceDTO, TouristService.class);
        assert transformed != null;
        transformed.setId(id);
        transformed.setName(touristServiceDTO.name());
        transformed.setPrice(touristServiceDTO.price());
        transformed.setDestiny(touristServiceDTO.destiny());
        transformed.setDate(touristServiceDTO.date());
        transformed.setDescription(touristServiceDTO.description());
        transformed.setServiceTypeEntity(conversionService.convert(touristServiceDTO.serviceTypeEntityDTO(), ServiceTypeEntity.class));
        transformed.setSalesList(touristServiceDTO.salesList().stream()
                .map(saleDTO -> conversionService.convert(saleDTO, Sale.class))
                .toList());
        touristServiceRepository.save(transformed);
    }

    @Override
    @Transactional
    public void delete (Long id) {
        Optional<TouristService> touristServiceOptional = touristServiceRepository.findById(id);
        if(touristServiceOptional.isEmpty()) {
            throw new TourismAgencyException(ApiError.TOURIST_SERVICE_NOT_FOUND);
        }
        touristServiceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TouristServiceDTO> getAll() {
        List<TouristService> touristServiceList = touristServiceRepository.findAll();
        return touristServiceList.stream()
                .map(touristService -> conversionService.convert(touristService, TouristServiceDTO.class))
                .toList();
    }
}
