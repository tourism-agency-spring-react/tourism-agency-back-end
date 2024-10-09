package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
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
            return ResponseEntity.status(HttpStatus.CREATED).body("Servicio turístico creado correctamente");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/service")
    public ResponseEntity<?> updateTouristService(@RequestBody @Valid TouristServiceDTO touristServiceDTO)  {
        try{
            touristServiceService.update(touristServiceDTO.id(), touristServiceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Servicio turístico actualizado correctamente");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<?> deleteTouristService(@PathVariable Long id) {
        try {
            touristServiceService.delete(id);
            return ResponseEntity.ok("Servicio turístico eliminado correctamente");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
