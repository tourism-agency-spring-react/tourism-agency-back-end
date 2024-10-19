package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.presentation.payload.MessageResponse;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
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
    public ResponseEntity<List<TouristPackageDTO>> getAllTouristPackages() {
        List<TouristPackageDTO> response = touristPackageService.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/package/{id}")
    public ResponseEntity<TouristPackageDTO> getTouristPackage(@Min(1) @PathVariable("id") Long id) {
        TouristPackageDTO response = touristPackageService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/package")
    public ResponseEntity<MessageResponse> createTouristPackage (@RequestBody @Valid TouristPackageDTO touristPackageDTO) {
        touristPackageService.save(touristPackageDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Registrado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/package/{id}")
    public ResponseEntity<MessageResponse> updateTouristPackage(@Min(1) @PathVariable("id") Long id, @RequestBody @Valid TouristPackageDTO touristPackageDTO) {
        touristPackageService.update(id, touristPackageDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Actualizado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/package/{id}")
    public ResponseEntity<MessageResponse> deleteTouristPackage(@Min(1) @PathVariable("id") Long id) {
        touristPackageService.delete(id);
        MessageResponse response = MessageResponse.builder()
                .message("Eliminado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
