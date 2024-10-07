package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "TouristService Controller", description = "Controller for TouristService crud")
public class TouristServiceController {

    private final ITouristService touristServiceService;

    @GetMapping("/services")
    @Operation(
            summary = "Get all TouristService",
            description = " Return all touristService from the data base in a format json list.",
            tags = { "TouristService Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristServiceDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristServiceDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getAllTouristService() {
        try {
            List<TouristServiceDTO> touristServiceDTOList = touristServiceService.getAll();
            return ResponseEntity.ok(touristServiceDTOList);
        }catch(EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/service/{id}")
    @Operation (
            summary = "Get a TouristService",
            description = "Return touristService by ID",
            tags = { "TouristService Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristServiceDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristServiceDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getTouristService(@PathVariable Long id) {
        try {
            TouristServiceDTO touristServiceDTO = touristServiceService.getById(id);
            return ResponseEntity.ok(touristServiceDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/service")
    @Operation (
            summary = "Create a TouristService",
            description = "Receives data touristService in json format to stores it in the data base and send confirm message in the body.",
            tags = { "TouristService Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristServiceDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristServiceDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> createTouristService(@RequestBody @Valid TouristServiceDTO touristServiceDTO) {
        try {
            touristServiceService.save(touristServiceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Servicio turístico creado correctamente");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/service")
    @Operation (
            summary = "Update a TouristService",
            description = "Find the touristService by ID to update her data base and send confirm message in the body.",
            tags = { "TouristService Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristServiceDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristServiceDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> updateTouristService(@RequestBody @Valid TouristServiceDTO touristServiceDTO)  {
        try{
            touristServiceService.update(touristServiceDTO.id(), touristServiceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Servicio turístico actualizado correctamente");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/service/{id}")
    @Operation (
            summary = "Delete a TouristService",
            description = "Find the touristService by ID to deletes it and send confirm message in the body.",
            tags = { "TouristService Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristServiceDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristServiceDTO
                                            .class)
                            )
                    )
            }
    )
    public ResponseEntity<?> deleteTouristService(@PathVariable Long id) {
        try {
            touristServiceService.delete(id);
            return ResponseEntity.ok("Servicio turístico eliminado correctamente");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
