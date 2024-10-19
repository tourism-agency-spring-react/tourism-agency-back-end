package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.service.implementation.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        try {
            CustomerDTO customerDTO = customerService.getById(id);
            return ResponseEntity.ok(customerDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAll () {
        try {
            List<CustomerDTO> customerDTOList = customerService.getAll();
            return ResponseEntity.ok(customerDTOList);
        }catch(EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<?> create (@RequestBody @Valid CustomerDTO customerDTO){
        try {
            customerService.save(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado correctamente");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/customer")
    public ResponseEntity<?> update (@RequestBody @Valid CustomerDTO customerDTO){
        try{
            customerService.update(customerDTO.id(), customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente actualizado correctamente");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        try {
            customerService.delete(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
}

