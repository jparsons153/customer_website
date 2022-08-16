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

    @OneToOne(mappedBy = "rentalcars")
    private Customer customer;

    @Override
    public String toString() {
        return "RentalCar{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
