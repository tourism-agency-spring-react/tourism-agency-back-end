package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController

public class TouristServiceController {

    private final ITouristService touristServiceService;

    @GetMapping("/services")
    public ResponseEntity<?> getAllTouristService() {
        try {
            List<TouristService> touristServices = touristServiceService.getAll();
            return ResponseEntity.ok(touristServices);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<?> getTouristService(@PathVariable Long id) {
        try {
            TouristService touristServices = touristServiceService.getById(id);
            return ResponseEntity.ok(touristServices);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/service")
    public ResponseEntity<?> createTouristService(@RequestBody TouristService touristService) {
      try {
          touristServiceService.save(touristService);
          return ResponseEntity.status(HttpStatus.CREATED).body(touristService);
      }catch (Exception e) {
          return ResponseEntity.notFound().build();
      }
    }

    @PutMapping("/service")
    public ResponseEntity<?> updateTouristService(@RequestBody TouristServiceDTO touristServiceDTO)  {
      try {
          touristServiceService.update(touristServiceDTO.id(),touristServiceDTO);
          return ResponseEntity.ok(touristServiceDTO);
      }catch (Exception e) {
          return ResponseEntity.notFound().build();
      }
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<Void> deleteTouristService(@PathVariable Long id) {
        try {
            touristServiceService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
