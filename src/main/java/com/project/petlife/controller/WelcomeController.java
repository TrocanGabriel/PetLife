package com.project.petlife.controller;

import com.oracle.tools.packager.Log;
import com.project.petlife.model.*;
import com.project.petlife.repository.ClinicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
@Slf4j
public class WelcomeController {

    private final ClinicRepository clinicRepository;

    public WelcomeController(ClinicRepository clinicService) {
        this.clinicRepository = clinicService;
    }

    @GetMapping("/")
    public String welcome(Model model) {
        log.info("Welcome to PetLife App");
        ArrayList<Clinic> results = this.clinicRepository.getAllBy();
        System.out.println(results.size());
        model.addAttribute("clinics", results);
        return "welcome";
    }

    @GetMapping("/createAppointment/{id}")
    public String addAppointment(@PathVariable  int id, Model model ){
        log.info("Calling method addAppointment! Creating appointment for clinic with id: {}", id);
        ArrayList<Clinic> clinicSelected = this.clinicRepository.getById(id);
        Log.info("Creating appointment from clinic");
        Clinic formClinic = clinicSelected.get(0);
        Appointment appointment = new Appointment();
        appointment.setClinic(formClinic);
        Set<Vet> vets = formClinic.getVets();
        model.addAttribute("appointment",appointment);
        model.addAttribute("vets",vets);
        model.addAttribute("clinics", clinicSelected);
        model.addAttribute("appointmentDetails",new AppointmentDetails());
        System.out.println("ADD appointment from welcome controller");
        return "appointments/addAppointment";
    }



}
