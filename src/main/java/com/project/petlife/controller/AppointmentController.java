package com.project.petlife.controller;


import com.project.petlife.model.Appointment;
import com.project.petlife.model.Owner;
import com.project.petlife.model.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {


    @PostMapping("/createAppointment")
    public String createAppointment() {

        return "/appointment";
    }


    @GetMapping(value = "/getpet", produces = "application/json")
    public @ResponseBody
    ArrayList<Pet> findPet(@RequestParam("ownerCNP") String ownerCNP) {
        ArrayList<Pet> pets = new ArrayList<>();
        System.out.println("HELLO " + ownerCNP);
        if (pets.isEmpty()) {
            Owner owner = new Owner();
            Pet pet = new Pet("pet", 1, owner);
            pets.add(pet);
        }
        return pets;
    }

}
