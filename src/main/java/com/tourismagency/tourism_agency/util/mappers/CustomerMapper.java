package com.tourismagency.tourism_agency.util.mappers;

import com.tourismagency.tourism_agency.dto.CustomerDTO;
import com.tourismagency.tourism_agency.model.Customer;

public class CustomerMapper {
    public static CustomerDTO customerToCustomerDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .phoneNumber(customer.getPhoneNumber())
                .direction(customer.getDirection())
                .birthDate(customer.getBirthDate())
                .build();
    }

    public static Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.id())
                .firstName(customerDTO.firstName())
                .lastName(customerDTO.lastName())
                .dni(customerDTO.dni())
                .phoneNumber(customerDTO.phoneNumber())
                .direction(customerDTO.direction())
                .birthDate(customerDTO.birthDate())
                .build();
    }
}
