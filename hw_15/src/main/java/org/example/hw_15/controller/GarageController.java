package org.example.hw_15.controller;

import lombok.RequiredArgsConstructor;
import org.example.hw_15.dto.AttachOrDetachCarsToGarage;
import org.example.hw_15.dto.request.GarageRequest;
import org.example.hw_15.facade.GarageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/garages")
public class GarageController {

    @Autowired
    private final GarageFacade garageFacade;

    @GetMapping()
    public String getGarages(Model model) {
        model.addAttribute("garages", garageFacade.findAll());
        return "pages/garages/garages_all";
    }

    @GetMapping("/new")
    public String getNewCarPage(Model model) {
        model.addAttribute("garage", new GarageRequest());
        return "pages/garages/garages_new";
    }

    @PostMapping("/new")
    public String newCar(@ModelAttribute("garage") GarageRequest garage) {
        garageFacade.create(garage);
        return "redirect:/garages";
    }

    @GetMapping("/update/{id}")
    public String getUpdateGaragePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("garage", garageFacade.findById(id));
        return "pages/garages/garages_update";
    }

    @PostMapping("update/{id}")
    public String updateGarage(@PathVariable("id") Long id, @ModelAttribute("garage") GarageRequest garageRequest) {
        garageFacade.update(garageRequest, id);
        return "redirect:/garages";
    }

    @GetMapping("/attach/{id}")
    public String getAttachCarToGaragePage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("garage", garageFacade.findById(id));
        model.addAttribute("nonAttachedCars", garageFacade.findByNotAttachToGarage(id));
        model.addAttribute("attachCarsToGarage", new AttachOrDetachCarsToGarage());
        return "pages/garages/garages_attach";
    }

    @PostMapping("/attach/{id}")
    public String attachCarsToGarage(@ModelAttribute("attachCarsToGarage") AttachOrDetachCarsToGarage carsToGarage, @PathVariable("id") Long garageId) {
        garageFacade.attachCarsToGarage(garageId, carsToGarage.getCarIds());
        return "redirect:/garages";
    }

    @GetMapping("/detach/{id}")
    public String getDetachCarFromGaragePage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("garage", garageFacade.findById(id));
        model.addAttribute("attachedCarsToGarage", garageFacade.findByAttachToGarage(id));
        model.addAttribute("detachCarsToGarage", new AttachOrDetachCarsToGarage());
        return "pages/garages/garages_detach";
    }

    @PostMapping("/detach/{id}")
    public String detachCarsFromGarage(@ModelAttribute("detachCarsToGarage") AttachOrDetachCarsToGarage detachCarsToGarage, @PathVariable("id") Long garageId) {
        garageFacade.detachCarsFromGarage(garageId, detachCarsToGarage.getCarIds());
        return "redirect:/garages";
    }

    @GetMapping("delete/{id}")
    public String deleteGarage(@PathVariable("id") Long id) {
        garageFacade.delete(id);
        return "redirect:/garages";
    }
}
