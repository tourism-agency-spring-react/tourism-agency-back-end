package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Validated
public class TouristServiceController {

    private final ITouristService touristServiceService;

    @GetMapping("/services")
    public ResponseEntity<?> getAllTouristService() {
        try {
            List<TouristServiceDTO> touristServiceDTOList = touristServiceService.getAll();
            return ResponseEntity.ok(touristServiceDTOList);
        }catch(EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<?> getTouristService(@PathVariable Long id) {
        try {
            TouristServiceDTO touristServiceDTO = touristServiceService.getById(id);
            return ResponseEntity.ok(touristServiceDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/service")
    public ResponseEntity<?> createTouristService(@RequestBody @Valid TouristServiceDTO touristServiceDTO) {
        try {
            touristServiceService.save(touristServiceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("TouristService created successfully");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/service")
    public ResponseEntity<?> updateTouristService(@RequestBody @Valid TouristServiceDTO touristServiceDTO)  {
        try{
            touristServiceService.update(touristServiceDTO.id(), touristServiceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("TouristService updated successfully");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<?> deleteTouristService(@PathVariable Long id) {
        try {
            touristServiceService.delete(id);
            return ResponseEntity.ok("TouristService deleted successfully");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
