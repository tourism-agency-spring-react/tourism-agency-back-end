package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class TouristPackageController {

    private final ITouristPackage touristPackageService;

    @GetMapping("/packages")
    public ResponseEntity<?> getAllTouristPackages() {
        try {
            List<TouristPackageDTO> touristPackageDTOList = touristPackageService.getAll();
            return ResponseEntity.ok(touristPackageDTOList);
        }catch(EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/package/{id}")
    public ResponseEntity<?> getTouristPackage(@Min(1) @PathVariable("id") Long id) {
        try {
            TouristPackageDTO touristPackageDTO = touristPackageService.getById(id);
            return ResponseEntity.ok(touristPackageDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/package")
    public ResponseEntity<?> createTouristPackage (@RequestBody @Valid TouristPackageDTO touristPackageDTO) {
        try {
            touristPackageService.save(touristPackageDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("TouristPackage created successfully. ");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/package/{id}")
    public ResponseEntity<?> updateTouristPackage(@Min(1) @PathVariable("id") Long id, @RequestBody @Valid TouristPackageDTO touristPackageDTO) {
        try{
            touristPackageService.update(id, touristPackageDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("TouristPackage updated successfully. ");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/package/{id}")
    public ResponseEntity<?> deleteTouristPackage(@Min(1) @PathVariable("id") Long id) {
        try {
            touristPackageService.delete(id);
            return ResponseEntity.ok("TouristPackage deleted successfully. ");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
