package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.persistense.model.TouristService;
import com.tourismagency.tourism_agency.persistense.repository.ITouristServiceRepository;
import com.tourismagency.tourism_agency.presentation.dto.TouristServiceDTO;
import com.tourismagency.tourism_agency.presentation.payload.MessageResponse;
import com.tourismagency.tourism_agency.service.interfaces.ITouristService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class TouristServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TouristServiceController.class);

    private final ITouristService touristServiceService;

    private final ITouristServiceRepository touristServiceRepository;

    private final ConversionService conversionService;

    @Autowired
    public TouristServiceController(ITouristService touristServiceService, ITouristServiceRepository touristServiceRepository,
                                    ConversionService conversionService) {
        this.touristServiceService = touristServiceService;
        this.touristServiceRepository = touristServiceRepository;
        this.conversionService = conversionService;
    }

    @GetMapping("/service/search")
    public List<TouristServiceDTO>searchService(
            @And({
                    @Spec(path = "name", spec = Equal.class),
                    @Spec(path = "destiny", spec = Equal.class),
                    @Spec(path = "date", spec = Equal.class)
            })
            Specification<TouristService> serviceSpec){

        List <TouristService> touristServiceList = touristServiceRepository.findAll(serviceSpec);
        return touristServiceList.stream()
                .map(touristService -> conversionService.convert(touristService, TouristServiceDTO.class))
                .toList();
    }

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
