package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.TouristPackageDTO;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import com.tourismagency.tourism_agency.service.interfaces.ITouristPackage;
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

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Tag(name = "TouristPackage Controller", description = "Controller for TouristPackage crud")
public class TouristPackageController {

    private final ITouristPackage touristPackageService;

    @GetMapping("/packages")
    @Operation (
            summary = "Get all TouristPackage",
            description = "Return all touristPackage from the data base in a format json list.",
            tags = { "TouristPackage Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristPackageDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristPackageDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getAllTouristPackages() {
        try {
            List<TouristPackageDTO> touristPackageDTOList = touristPackageService.getAll();
            return ResponseEntity.ok(touristPackageDTOList);
        }catch(EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/package/{id}")
    @Operation (
            summary = "Get a TouristPackage",
            description = "Return a touristPackage by ID",
            tags = { "TouristPackage Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristPackageDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristPackageDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> getTouristPackage(@PathVariable Long id) {
        try {
            TouristPackageDTO touristPackageDTO = touristPackageService.getById(id);
            return ResponseEntity.ok(touristPackageDTO);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/package")
    @Operation (
            summary = "Create a TouristPackage",
            description = "Receives data from touristPackage in json format to stores it in the data base and send confirm message in the body.",
            tags = { "TouristPackage Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data from touristPackage in format json",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristPackageDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristPackageDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> createTouristPackage (@RequestBody @Valid TouristPackageDTO touristPackageDTO) {
        try {
            touristPackageService.save(touristPackageDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Paquete turístico creado correctamente");
        }catch(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/package")
    @Operation (
            summary = "Update a TouristPackage",
            description = "Find a touristPackage by ID to updates her data base and send confirm message in the body.",
            tags = { "TouristPackage Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New data from touristPackage in format json for the update process.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristPackageDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristPackageDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> updateTouristPackage(@RequestBody @Valid TouristPackageDTO touristPackageDTO) {
        try{
            touristPackageService.update(touristPackageDTO.id(), touristPackageDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Paquete turístico actualizado correctamente");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/package/{id}")
    @Operation (
            summary = "Delete a TouristPackage",
            description = "Find the touristPackage by ID to deletes it and send confirm message in the body.",
            tags = { "TouristPackage Controller" },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Has no data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TouristPackageDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TouristPackageDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<?> deleteTouristPackage(@PathVariable Long id) {
        try {
            touristPackageService.delete(id);
            return ResponseEntity.ok("Paquete turístico eliminado correctamente");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
