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

    @OneToOne(targetEntity = ServiceTypeEntity.class, fetch = FetchType.EAGER)
    private ServiceTypeEntity serviceTypeEntity;

    @ManyToMany(mappedBy = "touristServicesList", targetEntity = Sale.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Sale> salesList;
}
