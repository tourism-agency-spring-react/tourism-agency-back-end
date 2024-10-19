package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.persistense.repository.ITouristPackageRepository;
import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.service.exception.ApiError;
import com.tourismagency.tourism_agency.service.exception.TourismAgencyException;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TouristPackageService implements ITouristPackage {

    private final ITouristPackageRepository touristPackageRepository;

    private final ConversionService conversionService;

    @Override
    @Transactional(readOnly = true)
    public TouristPackageDTO getById(Long id) {
        Optional<TouristPackage> touristPackageOptional = touristPackageRepository.findById(id);
        if (touristPackageOptional.isEmpty()) {
            throw new TourismAgencyException(ApiError.TOURIST_PACKAGE_NOT_FOUND);
        }else{
            return conversionService.convert(touristPackageOptional.get(), TouristPackageDTO.class);
        }
    }

    @Override
    @Transactional
    public void save(TouristPackageDTO touristPackageDTO) {
        if(Objects.nonNull(touristPackageDTO.id())){
            throw new TourismAgencyException(ApiError.TOURIST_PACKAGE_NOT_FOUND);
        }
        TouristPackage transformed = conversionService.convert(touristPackageDTO, TouristPackage.class);
        assert transformed != null;
        touristPackageRepository.save(transformed);
    }

    @Override
    @Transactional
    public void update(Long id, TouristPackageDTO touristPackageDTO) {
        if(touristPackageRepository.findById(id).isEmpty()){
            throw new TourismAgencyException(ApiError.TOURIST_PACKAGE_NOT_FOUND);
        }
        TouristPackage transformed = conversionService.convert(touristPackageDTO, TouristPackage.class);
        assert transformed != null;
        transformed.setId(id);
        transformed.setTouristServicesList(touristPackageDTO.touristServicesList().stream()
                .map(touristServiceDTO -> conversionService.convert(touristPackageDTO.touristServicesList(), TouristService.class))
                .toList());
        transformed.setSalesList(touristPackageDTO.salesList().stream()
                .map(saleDTO -> conversionService.convert(touristPackageDTO.salesList(), Sale.class))
                .toList());
        transformed.setPrice(touristPackageDTO.price());
        touristPackageRepository.save(transformed);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<TouristPackage> touristPackageOptional = touristPackageRepository.findById(id);
        if(touristPackageOptional.isEmpty()) {
            throw new TourismAgencyException(ApiError.TOURIST_PACKAGE_NOT_FOUND);
        }
        touristPackageRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TouristPackageDTO> getAll() {
        List<TouristPackage> touristPackageList = touristPackageRepository.findAll();
        return touristPackageList.stream()
                .map(touristPackage -> conversionService.convert(touristPackage, TouristPackageDTO.class))
                .toList();
    }
}


