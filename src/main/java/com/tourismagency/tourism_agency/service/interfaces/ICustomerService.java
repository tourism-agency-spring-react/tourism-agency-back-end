package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.presentation.dto.CustomerUpdateDTO;
import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;

import java.util.List;

public interface ICustomerService {

    public CustomerDTO getById (Long id);

    public List<CustomerDTO> getAll ();

    public void save (LoginRequestDTO loginRequestDTO);

    public void update (Long id, CustomerUpdateDTO customerUpdateDTO);

    public void delete (Long id);
}
