package org.example.hw_15.controller;

import lombok.RequiredArgsConstructor;

import org.example.hw_15.dto.request.CarRequest;
import org.example.hw_15.dto.response.CarResponse;
import org.example.hw_15.facade.CarFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private final CarFacade carFacade;

    @GetMapping
    public String getCars(Model model) {
        model.addAttribute("cars", carFacade.findAll());
        return "pages/cars/cars_all";
    }

    @GetMapping("/new")
    public String getNewCarPage(Model model) {
        model.addAttribute("car", new CarRequest());
        return "pages/cars/cars_new";
    }

    @PostMapping("/new")
    public String newCar(@ModelAttribute("car") CarRequest car) {
        carFacade.create(car);
        return "redirect:/cars";
    }

    @GetMapping("/update/{id}")
    public String getUpdateCarPage(@PathVariable("id") Long id, Model model) {
        CarResponse carResponse = carFacade.findById(id);
        model.addAttribute("car", carResponse);
        return "pages/cars/cars_update";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable("id") Long id, @ModelAttribute("car") CarRequest car) {
        carFacade.update(car, id);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        carFacade.delete(id);
        return "redirect:/cars";
    }
}
