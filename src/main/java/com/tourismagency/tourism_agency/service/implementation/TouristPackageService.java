package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.persistense.repository.ITouristPackageRepository;
import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
import com.tourismagency.tourism_agency.util.mapper.SaleMapper;
import com.tourismagency.tourism_agency.util.mapper.TouristPackageMapper;
import com.tourismagency.tourism_agency.util.mapper.TouristServiceMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TouristPackageService implements ITouristPackage {

    private final ITouristPackageRepository touristPackageRepository;

    @Override
    @Transactional(readOnly = true)
    public TouristPackageDTO getById(Long id) {
        TouristPackage touristPackage = touristPackageRepository.findById(id).orElse(null);

        if (touristPackage == null) {
            throw new ResourceNotFoundException("Tourist package: ", "id", id);
        } else {
            return TouristPackageMapper.touristPackageToTouristPackageDTO(touristPackage);
        }
    }

    @Override
    @Transactional
    public void save(TouristPackageDTO touristPackageDTO) {
        TouristPackage touristPackage = TouristPackageMapper.touristPackageDTOToTouristPackage(touristPackageDTO);

        Optional<TouristPackage> touristPackageOptional = touristPackageRepository.findById(touristPackage.getId());

        if (touristPackageOptional.isPresent()) {
            throw new IllegalArgumentException("The ID entered already exists in the data base");
        } else {
            touristPackageRepository.save(touristPackage);
        }
    }

    @Override
    @Transactional
    public void update(Long id, TouristPackageDTO touristPackageDTO) {
        Optional<TouristPackage> touristPackageOptional = touristPackageRepository.findById(id);

        if (touristPackageOptional.isPresent()) {
            TouristPackage touristPackage = touristPackageOptional.get();

            touristPackage.setTouristServicesList(touristPackageDTO.touristServicesList().stream()
                    .map(TouristServiceMapper::touristServiceDTOToTouristService)
                    .toList());

            touristPackage.setSalesList(touristPackageDTO.salesList().stream()
                    .map(SaleMapper::saleDTOToSale)
                    .toList());

            touristPackageRepository.save(touristPackage);
        } else {
            throw new IllegalArgumentException("The ID entered already exists in the data base");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<TouristPackage> touristPackageOptional = touristPackageRepository.findById(id);

        if (touristPackageOptional.isPresent()) {
            touristPackageRepository.delete(touristPackageOptional.get());
        } else {
            throw new EntityNotFoundException("Tourist Service not found.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TouristPackageDTO> getAll() {
        List<TouristPackage> touristPackageList = touristPackageRepository.findAll();

        if (touristPackageList.isEmpty()) {
            throw new EntityNotFoundException("List is empty.");
        } else {
            return touristPackageList.stream()
                    .map(TouristPackageMapper::touristPackageToTouristPackageDTO)
                    .toList();
        }
    }
}


