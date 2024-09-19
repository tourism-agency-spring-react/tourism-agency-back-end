package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    public CustomerDTO getById (Long id);

    public List<CustomerDTO> getAll ();

    public void save (CustomerDTO customerDTO);

    public void update (Long id, CustomerDTO customerDTO);

    public void delete (Long id);
}
