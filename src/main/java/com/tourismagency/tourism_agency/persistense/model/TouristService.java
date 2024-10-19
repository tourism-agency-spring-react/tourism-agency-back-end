package com.tourismagency.tourism_agency.persistense.model;

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

    @OneToOne(fetch = FetchType.EAGER)
    private CountryEntity destiny;

    private LocalDate date;

    private Double price;

    @OneToOne(targetEntity = ServiceTypeEntity.class, fetch = FetchType.EAGER)
    private ServiceTypeEntity serviceTypeEntity;

    @ManyToMany(mappedBy = "touristServicesList", targetEntity = Sale.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Sale> salesList;
}
