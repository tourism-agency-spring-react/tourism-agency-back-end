package com.tourismagency.tourism_agency.persistense.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode(exclude = {"touristServicesList"})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tourist_packages")
public class TouristPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;

    @ManyToMany(targetEntity = TouristPackage.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="tourist_package_tourist_service",
            joinColumns = @JoinColumn(name = "tourist_package_id"),
            inverseJoinColumns = @JoinColumn(name = "tourist_service_id"))
    private List<TouristService> touristServicesList;

    @ManyToMany(mappedBy = "touristPackagesList", targetEntity = Sale.class, fetch = FetchType.LAZY)
    private List<Sale> salesList;
}
