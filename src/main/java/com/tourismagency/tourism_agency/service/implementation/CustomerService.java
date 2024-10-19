package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.persistense.repository.ICustomerRepository;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.service.exception.ApiError;
import com.tourismagency.tourism_agency.service.exception.TourismAgencyException;
import com.tourismagency.tourism_agency.service.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final ConversionService conversionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            LOGGER.error("Customer with ID {} not found", id);
            throw new TourismAgencyException(ApiError.CUSTOMER_NOT_FOUND);
        }else{
            return conversionService.convert(customerOptional.get(), CustomerDTO.class);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> conversionService.convert(customer, CustomerDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public void save(CustomerDTO customerDTO) {
        if(Objects.nonNull(customerDTO.id())){
            LOGGER.error("Attempt to save a customer with an existing ID: {}", customerDTO.id());
            throw new TourismAgencyException(ApiError.CUSTOMER_WITH_SAME_ID);
        }

        Customer transformed = conversionService.convert(customerDTO, Customer.class);
        assert transformed != null;
        customerRepository.save(transformed);
        LOGGER.info("Customer created with ID: {}", transformed.getId());
    }

    @Override
    @Transactional
    public void update(Long id, CustomerDTO customerDTO) {
        if(customerRepository.findById(id).isPresent()) {
            LOGGER.error("Attempt to update non-existing customer with ID: {}", id);
            throw new TourismAgencyException(ApiError.CUSTOMER_NOT_FOUND);
        }
        Customer transformed = conversionService.convert(customerDTO, Customer.class);
        assert transformed != null;
        transformed.setId(id);
        transformed.setId(customerDTO.id());
        transformed.setFirstName(customerDTO.firstName());
        transformed.setLastName(customerDTO.lastName());
        transformed.setDni(customerDTO.dni());
        transformed.setPhoneNumber(customerDTO.phoneNumber());
        transformed.setBirthDate(customerDTO.birthDate());
        customerRepository.save(transformed);
        LOGGER.info("Customer with ID {} updated successfully", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(customerRepository.findById(id).isEmpty()) {
            LOGGER.error("Attempt to delete non-existing customer with ID: {}", id);
            throw new TourismAgencyException(ApiError.CUSTOMER_NOT_FOUND);
        }
        LOGGER.info("Customer with ID {} deleted successfully", id);
        customerRepository.deleteById(id);
    }
}
