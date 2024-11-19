package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.persistense.model.UserEntity;
import com.tourismagency.tourism_agency.persistense.repository.ICustomerRepository;
import com.tourismagency.tourism_agency.persistense.repository.IUserRepository;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.presentation.dto.CustomerUpdateDTO;
import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.service.exception.ApiError;
import com.tourismagency.tourism_agency.service.exception.TourismAgencyException;
import com.tourismagency.tourism_agency.service.interfaces.ICustomerService;
import com.tourismagency.tourism_agency.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final ConversionService conversionService;

    private final IUserService userService;

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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
    public void save(LoginRequestDTO loginRequestDTO) {

        UserEntity newUser = userService.createCustomerUser(loginRequestDTO);
        LOGGER.info("Creating new Customer");
        Customer customer = Customer
                .builder()
                .user(newUser)
                .build();

        customerRepository.save(customer);
        LOGGER.info("Customer saved with ID {}", customer.getId());
    }

    @Override
    @Transactional
    public void update(Long id, CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            LOGGER.error("Attempt to update non-existing customer with ID: {}", id);
            return new TourismAgencyException(ApiError.CUSTOMER_NOT_FOUND);
        });

        LOGGER.info("Updating user of Customer with id: {}", customer.getUser().getId());
        userRepository.findById(customer.getUser().getId()).ifPresent(user -> {
            user.setEmail(customerUpdateDTO.email());
            user.setPassword(passwordEncoder.encode(customerUpdateDTO.password()));
        });

        LOGGER.info("Updating customer");
        customer.setFirstName(customerUpdateDTO.firstName());
        customer.setLastName(customerUpdateDTO.lastName());
        customer.setDni(customerUpdateDTO.dni());
        customer.setPhoneNumber(customerUpdateDTO.phoneNumber());
        customer.setBirthDate(customerUpdateDTO.birthDate());
        customer.setDirection(customerUpdateDTO.direction());
        customerRepository.save(customer);
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
