package com.tourismagency.tourism_agency.persistense.repository;

import com.tourismagency.tourism_agency.persistense.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
}