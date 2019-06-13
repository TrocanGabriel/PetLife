package com.project.petlife.controller;

import com.project.petlife.model.*;
import com.project.petlife.repository.ClinicRepository;
import com.project.petlife.repository.OwnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

@Controller
public class WelcomeController {

    private final ClinicRepository clinics;

    public WelcomeController(ClinicRepository clinicService) {
        this.clinics = clinicService;
    }

    @GetMapping("/")
    public String welcome(Model model) {
        ArrayList<Clinic> results = this.clinics.findByName("");

        System.out.println(results);
        model.addAttribute("clinics", results);

        return "welcome";
    }

    @GetMapping("/createAppointment/{id}")
    public String addAppointment(@PathVariable  int id, Model model ){
        ArrayList<Clinic> clinicSelected = this.clinics.findById(id);
        Clinic formClinic = clinicSelected.get(0);
        Appointment appointment = new Appointment();
        appointment.setClinic(formClinic);
        Vet vetDummy = new Vet("ion", "x", 2, formClinic);
        Set<Vet> vets = formClinic.getVets();
        model.addAttribute("appointment",appointment);
        model.addAttribute("vets",vets);

        return "appointments/addAppointment";
    }



}
