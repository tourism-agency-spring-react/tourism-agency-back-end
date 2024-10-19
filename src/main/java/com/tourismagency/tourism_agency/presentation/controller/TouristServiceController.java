package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.presentation.payload.MessageResponse;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
    public ResponseEntity<List<TouristServiceDTO>> getAllTouristService() {
        List<TouristServiceDTO> response = touristServiceService.getAll();
        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<TouristServiceDTO> getTouristService(@Min(1) @PathVariable("id") Long id) {
        TouristServiceDTO response = touristServiceService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/service")
    public ResponseEntity<MessageResponse> createTouristService(@RequestBody @Valid TouristServiceDTO touristServiceDTO) {
        touristServiceService.save(touristServiceDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Registrado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<MessageResponse> updateTouristService(@Min(1) @PathVariable("id") Long id, @RequestBody @Valid TouristServiceDTO touristServiceDTO)  {
        touristServiceService.update(id, touristServiceDTO);
        MessageResponse response = MessageResponse.builder()
                .message("Actualizado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<MessageResponse> deleteTouristService( @Min(1) @PathVariable("id") Long id) {
        touristServiceService.delete(id);
        MessageResponse response = MessageResponse.builder()
                .message("Eliminado correctamente.")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
