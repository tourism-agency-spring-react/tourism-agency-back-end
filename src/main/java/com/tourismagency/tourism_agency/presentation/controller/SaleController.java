package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/s")
@RequiredArgsConstructor
@Validated
public class SaleController {

    private final ISaleService saleService;

    @GetMapping("/sales")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> saleDTOList = saleService.getAllSales();
        return ResponseEntity.ok(saleDTOList);
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable Long id) {
        try {
            SaleDTO saleDTO = saleService.getSaleById(id);
            return ResponseEntity.ok(saleDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/sale")
    public ResponseEntity<?> createSale(@RequestBody @Valid SaleDTO saleDTO) {
        try {
            saleService.saveSale(saleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sale created successfully");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody @Valid SaleDTO saleDTO) {
        try {
            saleService.updateSale(id, saleDTO);
            return ResponseEntity.ok("Sale updated successfully");
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/sale/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        try {
            saleService.deleteSale(id);
            return ResponseEntity.ok("Sale deleted successfully");
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
