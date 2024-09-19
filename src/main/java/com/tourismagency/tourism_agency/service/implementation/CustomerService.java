package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.persistense.repository.ICustomerRepository;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.service.interfaces.ICustomerService;
import com.tourismagency.tourism_agency.util.mapper.CustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new EntityNotFoundException("Customer not found");
        }else{
            return CustomerMapper.customerToCustomerDto(customer);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAll() {
        List<Customer> customerList = customerRepository.findAll();

        if (customerList.isEmpty()) {
            throw new EntityNotFoundException("List is empty.");
        }else{
            return customerList.stream()
                    .map(CustomerMapper::customerToCustomerDto)
                    .toList();
        }
    }

    @Override
    @Transactional
    public void save(CustomerDTO customerDTO) {
        Customer customerSave = CustomerMapper.customerDTOToCustomer(customerDTO);

        if(customerRepository.findByDni(customerSave.getDni()).isPresent()){
            throw new IllegalArgumentException("The ID entered already exists in the data base");
        }else{
            customerRepository.save(customerSave);
        }
    }

    @Override
    @Transactional
    public void update(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if(customerOptional.isPresent()){
            customerOptional.get().setId(customerDTO.id());
            customerOptional.get().setFirstName(customerDTO.firstName());
            customerOptional.get().setLastName(customerDTO.lastName());
            customerOptional.get().setDni(customerDTO.dni());
            customerOptional.get().setPhoneNumber(customerDTO.phoneNumber());
            customerRepository.save(customerOptional.get());
        }else{
            throw new IllegalArgumentException("The ID entered already exists in the data base");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()) {
            customerRepository.delete(customer.get());
        }else{
            throw new EntityNotFoundException("Customer not found.");
        }
    }
}
