package com.project.petlife.controller;


import com.project.petlife.model.*;
import com.project.petlife.repository.AppointmentRepository;
import com.project.petlife.repository.ClinicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final ClinicRepository clinics;


    public AppointmentController(AppointmentRepository appointmentRepository, ClinicRepository clinics) {
        this.appointmentRepository = appointmentRepository;
        this.clinics = clinics;
    }

    @GetMapping("/search")
    public String findAppointment(Appointment appointment,Model model) {
        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(appointment.getOwner().getCnp());

        model.addAttribute("ownerAppointments",ownerAppointments);
        return "appointments/appointmentsList";
    }

    @GetMapping("/findAppointment")
    public String appointments(Model model){
        model.addAttribute("appointment", new Appointment());
        return "appointments/manageAppointments";
    }

    @GetMapping("/newAppointment")
    public String newAppointment(Model model){
        ArrayList<Clinic> clinics = this.clinics.getAllBy();
        model.addAttribute("clinics", clinics);
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "appointments/addAppointment";
    }

    @PostMapping("/createAppointment")
    public String createAppointment(Appointment appointment, Model model){
        appointmentRepository.save(appointment);
        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(appointment.getOwner().getCnp());

        model.addAttribute("ownerAppointments",ownerAppointments);
        return "appointments/appointmentsList";
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
