package com.tourismagency.tourism_agency.persistense.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@EqualsAndHashCode(exclude = {"touristServicesList","touristPackageList"})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name="create_at", columnDefinition = "DATE")
    private LocalDate createAt;

    @OneToOne(fetch = FetchType.EAGER)
    private PaymentMethodEntity paymentMethod;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.PERSIST)
    private Customer customer;

    @ManyToMany(targetEntity = TouristService.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "sale_id_tourist_service_id",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "tourist_service_id")
    )
    private List<TouristService> touristServicesList;

    @ManyToMany(targetEntity = TouristPackage.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "sale_id_tourist_package_id",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "tourist_package_id")
    )
    private List<TouristPackage> touristPackagesList;

    @PrePersist
    public void prePersist(){
        this.createAt = LocalDate.now();
    }

}
