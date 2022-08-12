package com.website.customer_site.services;

import com.website.customer_site.models.Customer;
import com.website.customer_site.models.RentalCar;
import com.website.customer_site.repos.RentalCarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalServiceImpl {
// implements CustomerService interface - return type is different??

    @Autowired
    final RentalCarsRepository rentalCarsRepository;

    @Transactional
    public List<RentalCar> getAllCars() {
        return rentalCarsRepository.findAll();
    }

    @Transactional
    public RentalCar saveRentalCar(RentalCar rentalCar) {
        return rentalCarsRepository.save(rentalCar);
    }
//
//    @Override
//    public Customer getCustomer(Long id) {
//        return null;
//    }
//
//    @Override
//    public void deleteCustomer(Long id) {
//
//    }
//
//    @Override
//    public List<Customer> saveAllCustomer(List<Customer> customerList) {
//        return null;
//    }
}
