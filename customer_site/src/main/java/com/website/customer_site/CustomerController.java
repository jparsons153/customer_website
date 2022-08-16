package com.website.customer_site;

import com.website.customer_site.models.Customer;
import com.website.customer_site.models.RentalCar;
import com.website.customer_site.services.CustomerService;
import com.website.customer_site.services.RentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService, RentalServiceImpl rentalService) {
        this.customerService = customerService;
        this.rentalService = rentalService;
    }

    private RentalServiceImpl rentalService;
    public void RentalCarController(RentalServiceImpl rentalService){this.rentalService = rentalService;}


    @GetMapping("/")
    public String viewHomePage(Model model) {
        // Here you call the service to retrieve all the customers
        final List<Customer> customerList = customerService.getAllCustomers();
        // Once the customers are retrieved, you can store them in model and return it to the view
        model.addAttribute("customerList", customerList);
        return "index";
    }

    @GetMapping("/new")
    public String showNewCustomerPage(Model model) {
        // Here a new (empty) Customer is created and then sent to the view
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new-customer";
    }

    @PostMapping(value = "/save")
    // As the Model is received back from the view, @ModelAttribute
    // creates a Customer based on the object you collected from
    // the HTML page above
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    // The path variable "id" is used to pull a customer from the database
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        // create ModelAndView error page
        ModelAndView errorMV = new ModelAndView("error-page");
        Customer customer = customerService.getCustomer(id);
        // check that id is a valid customer id value
            if (!id.equals(customer.getId())) {
                errorMV.addObject("message",
                        "Cannot update, customer id " + customer.getId()
                                + " doesn't match id to be updated: " + id + ".");
            return errorMV;
            }
            ModelAndView mav = new ModelAndView("edit-customer");
            mav.addObject("customer", customer);
            return mav;
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable(name = "id") Long id, @ModelAttribute("customer") Customer customer, Model model) {
        if (!id.equals(customer.getId())) {
            model.addAttribute("message",
                    "Cannot update, customer id " + customer.getId()
                            + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.removeRentalcar(id);
        customerService.deleteCustomer(id);
        return "redirect:/";
    }

    @GetMapping("/assign/{id}")
    ModelAndView showRentalCarPage(@PathVariable(name = "id") Long id){
        Customer customer = customerService.getCustomer(id);
        ModelAndView mav = new ModelAndView("assign-car");
        mav.addObject("customer", customer);

        final List<RentalCar> rentalCarList = rentalService.getAllCars();
        mav.addObject("rentalCarList", rentalCarList);
        return mav;
    }

    @RequestMapping("/remove/{id}")
    public String removeCarAssigned(@PathVariable(name = "id") Long id) {
        customerService.removeRentalcar(id);
        return "redirect:/";
    }


    @PostMapping("/update-customer/")
    public String carUpdate(@RequestParam("customerId")Long customerId,@RequestParam("rentalCarId")Long rentalCarId) {

        customerService.assignRentalcar(customerId,rentalCarId);

        System.out.println(customerId);
        System.out.println(rentalCarId);
        return "redirect:/";
    }
}