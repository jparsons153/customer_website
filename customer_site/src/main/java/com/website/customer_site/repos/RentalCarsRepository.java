package com.website.customer_site.repos;

import com.website.customer_site.models.Customer;
import com.website.customer_site.models.RentalCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCarsRepository extends JpaRepository<RentalCar, Long> {
}
