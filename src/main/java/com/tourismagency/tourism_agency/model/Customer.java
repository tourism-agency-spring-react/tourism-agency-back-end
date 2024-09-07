package com.tourismagency.tourism_agency.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String dni;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String direction;

    @Temporal(TemporalType.DATE)
    @Column(name="birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;
}
