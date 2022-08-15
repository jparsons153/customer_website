package com.website.customer_site.services;

import com.website.customer_site.models.Customer;
import com.website.customer_site.models.RentalCar;
import com.website.customer_site.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    final CustomerRepository customerRepository;

    @Autowired
    final RentalServiceImpl rentalService;

    // The findAll function gets all the customers by doing a SELECT query in the DB.
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // The save function uses an INSERT query in the DB.
    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // The findById function uses a SELECT query with a WHERE clause in the DB.
    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElse(null);
    }

    // The deleteById function deletes the customer by doing a DELETE in the DB.
    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // The saveAll function would do multiple INSERTS into the DB.
    @Override
    @Transactional
    public List<Customer> saveAllCustomer(List<Customer> customerList) {
        return customerRepository.saveAll(customerList);
    }

    // create method to assign car
    // fetch customer + car based on id
    // update customer with car & save to database
    @Transactional
    public void assignRentalcar(Long customerId, Long rentalCarId){
        Customer customerAssigned = getCustomer(customerId);
        System.out.println("customer id from service method " + customerId);
        RentalCar rentalCarAssigned = rentalService.getRentalCar(rentalCarId);

//        customerAssigned.setFullName("Joe Bloggs");
        customerAssigned.setRentalcars(rentalCarAssigned);
        saveCustomer(customerAssigned);
    }
}