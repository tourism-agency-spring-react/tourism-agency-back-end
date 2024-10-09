package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
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
    public ResponseEntity<?> getTouristPackage(@PathVariable Long id) {
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
            return ResponseEntity.status(HttpStatus.CREATED).body("Paquete turístico creado correctamente");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/package")
    public ResponseEntity<?> updateTouristPackage(@RequestBody @Valid TouristPackageDTO touristPackageDTO) {
        try{
            touristPackageService.update(touristPackageDTO.id(), touristPackageDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Paquete turístico actualizado correctamente");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/package/{id}")
    public ResponseEntity<?> deleteTouristPackage(@PathVariable Long id) {
        try {
            touristPackageService.delete(id);
            return ResponseEntity.ok("Paquete turístico eliminado correctamente");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
