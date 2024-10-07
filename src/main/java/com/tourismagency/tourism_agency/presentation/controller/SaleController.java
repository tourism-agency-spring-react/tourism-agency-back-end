package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import com.tourismagency.tourism_agency.service.interfaces.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/s")
@RequiredArgsConstructor
@Tag(name = "Sale Controller", description = "Controller for sales crud ")
public class SaleController {

    private final ISaleService saleService;

    @GetMapping("/sales")
    @Operation(
            summary = "Get a sales",
            description = "Return all sales from the data base in a format json list",
            tags = {"Sale Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaleDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaleDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> saleDTOList = saleService.getAllSales();
        return ResponseEntity.ok(saleDTOList);
    }

    @GetMapping("/sale/{id}")
    @Operation(
            summary = "Get a sale",
            description = "Return a sale by ID",
            tags = {"Sale Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaleDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaleDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getSaleById(@PathVariable Long id) {
        try {
            SaleDTO saleDTO = saleService.getSaleById(id);
            return ResponseEntity.ok(saleDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/sale")
    @Operation(
            summary = "Create a sale",
            description = "Receives data from sale in json format to stores it in the data and send confirm message in the body. ",
            tags = {"Sale Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data from sale in format json.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaleDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaleDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> createSale(@RequestBody @Valid SaleDTO saleDTO) {
        try {
            saleService.saveSale(saleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sale created successfully");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/sale/{id}")
    @Operation(
            summary = "Update a customer",
            description = "Find a sale by ID to update her data in the data base and send confirm message i the body. ",
            tags = {"Sale Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New data from sale in format json for the update process",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaleDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaleDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody @Valid SaleDTO saleDTO) {
        try {
            saleService.updateSale(id, saleDTO);
            return ResponseEntity.ok("Sale updated successfully");
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/sale/{id}")
    @Operation(
            summary = "Delete a customer",
            description = "Finds the sales by ID to delete it and send confirm message in the body. ",
            tags = {"Sale Controller"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaleDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaleDTO.class)
                            )
                    )
            }
    )


    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        try {
            saleService.deleteSale(id);
            return ResponseEntity.ok("Sale deleted successfully");
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
