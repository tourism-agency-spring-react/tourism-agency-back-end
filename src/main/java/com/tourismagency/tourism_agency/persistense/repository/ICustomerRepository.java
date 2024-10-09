package com.tourismagency.tourism_agency.persistense.repository;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.dni = :dni")
    Optional<Customer> findByDni(@Param("dni")String dni);
}
