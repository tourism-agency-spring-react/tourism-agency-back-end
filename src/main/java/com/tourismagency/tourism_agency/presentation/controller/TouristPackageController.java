package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.persistense.model.TouristPackage;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
import jakarta.persistence.EntityNotFoundException;
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

    @GetMapping
    public ResponseEntity<List<TouristPackage>> getAllTouristPackages() {
        try {
            List<TouristPackage> touristPackages = touristPackageService.getAll();
            return ResponseEntity.ok(touristPackages);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TouristPackage> getTouristPackage(@PathVariable Long id) {
        try {
            TouristPackage touristPackage = touristPackageService.getById(id);
            return ResponseEntity.ok(touristPackage);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TouristPackage> createTouristPackage (@RequestBody TouristPackage touristPackage) {
        try {
            touristPackageService.save(touristPackage);
            return ResponseEntity.status(HttpStatus.CREATED).body(touristPackage);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TouristPackage> updateTouristPackage(@PathVariable Long id, @RequestBody TouristPackage touristPackage) {
        try {
            touristPackageService.update(id, touristPackage);
            return ResponseEntity.ok(touristPackage);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTouristPackage(@PathVariable Long id) {
        try {
            touristPackageService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
