package com.project.petlife.controller;

import com.project.petlife.model.Clinic;
import com.project.petlife.model.Owner;
import com.project.petlife.model.Vet;
import com.project.petlife.repository.ClinicRepository;
import com.project.petlife.repository.OwnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Controller
public class WelcomeController {

    private final ClinicRepository clinics;

    public WelcomeController(ClinicRepository clinicService) {
        this.clinics = clinicService;
    }

    @GetMapping("/")
    public String welcome(Model model) {
        Collection<Clinic> results = this.clinics.findByName("");

        System.out.println(results);
        model.addAttribute("clinics", results);

        return "welcome";
    }

    @GetMapping("/createAppointment/{id}")
    public String addAppointment(@PathVariable  int id, Model model ){
        model.addAttribute("clinic",id);
        return "/appointments/addAppointment";
    }



}
