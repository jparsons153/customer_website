package com.website.customer_site;

import com.website.customer_site.models.RentalCar;
import com.website.customer_site.services.RentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RentalCarController {

    @Autowired
    private final RentalServiceImpl rentalService;
    public RentalCarController(RentalServiceImpl rentalService){this.rentalService = rentalService;}

    @GetMapping("/newCar")
    public String createNewCar(Model carModel){
        RentalCar rentalCar = new RentalCar();
        carModel.addAttribute("rentalCar", rentalCar);
        return "new-car";
    }

    @PostMapping(value = "/save-car")
    public String saveRentalCar(@ModelAttribute("rentalCar") RentalCar rentalCar){
        rentalService.saveRentalCar(rentalCar);
        return "redirect:/";
    }

    @GetMapping("/rentalcar/Index")
    public String carIndex(Model carModel){
        final List<RentalCar> carList = rentalService.getAllCars();
        carModel.addAttribute("carList",carList);
        return "carIndex";
    }
}