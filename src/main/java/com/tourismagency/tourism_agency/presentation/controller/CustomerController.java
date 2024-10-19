package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.documentation.CustomerResource;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.presentation.payload.MessageResponse;
import com.tourismagency.tourism_agency.service.implementation.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class CustomerController implements CustomerResource {

    private final CustomerService customerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> get(@Min(1) @PathVariable("id") Long id) {
        LOGGER.info("Get customer with id {}", id);
        CustomerDTO response = customerService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAll() {
        LOGGER.info("Getting all customers");
        List<CustomerDTO> response = customerService.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customer")
    public ResponseEntity<MessageResponse> create (@RequestBody @Valid CustomerDTO customerDTO){
        LOGGER.info("Creating customer");
        customerService.save(customerDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Registrado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<MessageResponse> update (@Min(1) @PathVariable ("id") Long id, @RequestBody @Valid CustomerDTO customerDTO){
        LOGGER.info("Updating customer with id {}", id);
        customerService.update(id, customerDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Actualizado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<MessageResponse> delete (@PathVariable("id") Long id){
        LOGGER.info("Deleting customer with id {}", id);
        customerService.delete(id);
        MessageResponse response = MessageResponse.builder()
                .message("Eliminado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

