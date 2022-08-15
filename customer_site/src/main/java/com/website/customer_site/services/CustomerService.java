package com.website.customer_site.services;

import com.website.customer_site.models.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomer(Long id);

    void deleteCustomer(Long id);

    List<Customer> saveAllCustomer(List<Customer> customerList);

    // add method to interface to assign car to customer
    void assignRentalcar(Long customerId, Long rentalCarId);
}
