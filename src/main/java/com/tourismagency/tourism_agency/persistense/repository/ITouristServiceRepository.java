package com.tourismagency.tourism_agency.persistense.repository;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ITouristServiceRepository extends JpaRepository<TouristService, Long>, JpaSpecificationExecutor<TouristService> {
}
