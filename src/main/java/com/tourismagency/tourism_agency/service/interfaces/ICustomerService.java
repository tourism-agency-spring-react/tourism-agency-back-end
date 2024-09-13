package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.persistense.model.Customer;

import java.util.List;

public interface ICustomerService {

    public Customer getById (Long id);

    public List<Customer> getAll ();

    public void save (Customer customer);

    public void update (Long id, Customer customer);

    public void delete (Long id);
}
