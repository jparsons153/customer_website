package com.website.customer_site.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rentalcars")
@Builder
@Getter
@Setter

public class RentalCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private String registration;

    // bi-directional or uni directional?
    @OneToOne(mappedBy = "rentalcars")
    private Customer customer;
}
