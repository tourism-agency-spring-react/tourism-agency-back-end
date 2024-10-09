package com.tourismagency.tourism_agency.persistense.model;

import com.tourismagency.tourism_agency.enums.CountryEnum;
import com.tourismagency.tourism_agency.enums.ServiceTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@EqualsAndHashCode(exclude = {"salesList"})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tourist_services")
public class TouristService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated (EnumType.STRING)

    private CountryEnum destiny;

    private LocalDate date;

    private Double price;

    @Enumerated (EnumType.STRING)
    private ServiceTypeEnum serviceType;

    @ManyToMany(mappedBy = "touristServicesList", targetEntity = Sale.class, fetch = FetchType.LAZY)
    private List<Sale> salesList;
}
