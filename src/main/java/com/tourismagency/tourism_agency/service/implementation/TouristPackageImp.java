package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.persistense.repository.ITouristPackageRepository;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TouristPackageImp implements ITouristPackage {

        private final ITouristPackageRepository touristPackageRepository;

        @Override
        public TouristPackage getById (Long id) {
            return touristPackageRepository.findById(id).orElse(null);
        }

        @Override
        @Transactional
        public void save (TouristPackage touristPackage) {
            touristPackageRepository.save(touristPackage);
        }

        @Override
        @Transactional
        public void update (Long id, TouristPackage touristPackage) {
            TouristPackage touristPackageGet = this.getById(id);

            touristPackageGet.setPrice(touristPackage.getPrice());

            touristPackageRepository.save(touristPackageGet);

        }

        @Override
        @Transactional
        public void delete (Long id) {
            touristPackageRepository.deleteById(id);
        }

        @Override
        public List<TouristPackage> getAll() {
            return touristPackageRepository.findAll();
        }

    }


