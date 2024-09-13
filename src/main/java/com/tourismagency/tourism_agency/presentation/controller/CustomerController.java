package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.persistense.model.Customer;
import com.tourismagency.tourism_agency.service.implementation.CustomerService;
import com.tourismagency.tourism_agency.util.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        CustomerDTO customerDTO = CustomerMapper.customerToCustomerDto(customerService.getById(id));
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAll(){
        List<Customer> customerList = customerService.getAll();
        List<CustomerDTO> customerDTOList = customerList.stream()
                .map(CustomerMapper::customerToCustomerDto)
                .toList();
        return ResponseEntity.ok(customerDTOList);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> create(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.customerDTOToCustomer(customerDTO);
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado correctamente");
    }

    @PutMapping("/customer")
    public ResponseEntity<?> update(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.customerDTOToCustomer(customerDTO);
        customerService.update(customer.getId(), customer);
        return ResponseEntity.ok("Cliente actualizado correctamente");
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }

}
