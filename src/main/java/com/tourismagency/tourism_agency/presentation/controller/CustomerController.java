package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
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
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name="Customer Controller", description = "Controller for customers crud")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{id}")
    @Operation(
            summary = "Get a customer",
            description = "Return a customer by ID",
            tags = {"Customer Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        try {
            CustomerDTO customerDTO = customerService.getById(id);
            return ResponseEntity.ok(customerDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/customers")
    @Operation(
            summary = "Get all customers",
            description = "Return all customers from the data base in a format json list.",
            tags = {"Customer Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getAll () {
        try {
            List<CustomerDTO> customerDTOList = customerService.getAll();
            return ResponseEntity.ok(customerDTOList);
        }catch(EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customer")
    @Operation(
            summary = "Create a customer",
            description = "Receives data from customer in json format to stores it in the data base and send confirm message in the body.",
            tags = {"Customer Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data from customer in format json.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> create (@RequestBody @Valid CustomerDTO customerDTO){
        try {
            customerService.save(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado correctamente");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/customer")
    @Operation(
            summary = "Update a customer",
            description = "Find a customer by ID to updates her data in the data base and send confirm message in the body.",
            tags = {"Customer Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New data from customer in format json for the update process.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> update (@RequestBody @Valid CustomerDTO customerDTO){
        try{
            customerService.update(customerDTO.id(), customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente actualizado correctamente");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/customer/{id}")
    @Operation(
            summary = "Delete a customer",
            description = "Finds the customer by ID to deletes it and send confirm message in the body.",
            tags = {"Customer Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomerDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        try {
            customerService.delete(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
}

