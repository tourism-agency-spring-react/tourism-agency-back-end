package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.SaleDTO;
import com.tourismagency.tourism_agency.presentation.payload.MessageResponse;
import com.tourismagency.tourism_agency.service.interfaces.ISaleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleController.class);

    @GetMapping("/sales")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        LOGGER.info("Getting all salles");
        List<SaleDTO> response = saleService.getAllSales();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@Min(1) @PathVariable("id") Long id) {
        LOGGER.info("Getting sale with id {}", id);
        SaleDTO response = saleService.getSaleById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sale")
    public ResponseEntity<MessageResponse> createSale(@RequestBody @Valid SaleDTO saleDTO) {
        LOGGER.info("Creating sale");
        saleService.saveSale(saleDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Registrado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<MessageResponse> updateSale(@Min(1) @PathVariable("id") Long id, @RequestBody @Valid SaleDTO saleDTO) {
        LOGGER.info("Updating sale with id {}", id);
        saleService.updateSale(id, saleDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Actualizado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/sale/{id}")
    public ResponseEntity<MessageResponse> deleteSale(@Min(1) @PathVariable("id") Long id) {
        LOGGER.info("Deleting customer with id {}", id);
        saleService.deleteSale(id);
        MessageResponse response = MessageResponse.builder()
                .message("Eliminado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
