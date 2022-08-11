package com.website.customer_site;

import com.website.customer_site.models.Customer;
import com.website.customer_site.services.CustomerService;
import com.website.customer_site.services.RentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CustomerSiteApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RentalServiceImpl rentalService;

	// The main method is defined here which will start your application
	public static void main(String[] args) {
		SpringApplication.run(CustomerSiteApplication.class);
	}

	// You can also define a run method which performs an operation at runtime
	// In this example, the run method saves some Customer data into the database for testing
	@Override
	public void run(String... args) throws Exception {
		if (customerService.getAllCustomers().isEmpty()) {
			// create customers
			customerService.saveAllCustomer(Arrays.asList(
							Customer.builder()
									.fullName("Customer 1")
									.emailAddress("customer1@gmail.com")
									.address("Customer Address One")
									.age(30)
									.build(),
							Customer.builder().fullName("Customer 2").emailAddress("customer2@gmail.com").address("Customer Address Two").age(28).build(),
							Customer.builder().fullName("Customer 3").emailAddress("customer3@gmail.com").address("Customer Address Three").age(32).build()
					)
			);
		}
	}
}
