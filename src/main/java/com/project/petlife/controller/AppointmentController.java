package com.project.petlife.controller;


import com.project.petlife.model.*;
import com.project.petlife.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final ClinicRepository clinicRepository;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final OwnerRepository ownerRepository;

    public AppointmentController(AppointmentRepository appointmentRepository, ClinicRepository clinicRepository, PetRepository petRepository, VetRepository vetRepository, OwnerRepository ownerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.clinicRepository = clinicRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
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

    @PostMapping("/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable int id, Model model){
        Appointment appointment = appointmentRepository.findById(id);
        appointmentRepository.delete(appointment);
        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(appointment.getOwner().getCnp());
        model.addAttribute("ownerAppointments",ownerAppointments);
        return "appointments/appointmentsList";
    }

    @GetMapping("/newAppointment")
    public String newAppointment(Model model){
        ArrayList<Clinic> clinics = this.clinicRepository.getAllBy();
        model.addAttribute("clinics", clinics);
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        return "appointments/addAppointment";
    }

    @GetMapping("/editAppointment/{id}")
    public String edit(@PathVariable int id, Model model){
        Appointment appointment = appointmentRepository.findById(id);
        ArrayList<Clinic> clinics = this.clinicRepository.getAllBy();
        ArrayList<Pet> pets = this.petRepository.findByOwnerCNP(appointment.getPet().getOwner().getCnp());
        System.out.println("VET : " +appointment.getVet().getLastName());
        model.addAttribute("pets", pets);
        model.addAttribute("clinics", clinics);
        model.addAttribute("appointment", appointment);
        return "appointments/editAppointment";

    }

    @PostMapping("/createAppointment")
    public String createAppointment(Appointment appointment, Model model){
        Appointment newApp = new Appointment();
        System.out.println(appointment.getDescription() + " DESC ");
        newApp.setClinic(clinicRepository.findById(appointment.getClinic().getId()));
        newApp.setOwner(ownerRepository.getByCnp(appointment.getOwner().getCnp()));
        System.out.println("PET " + appointment.toString());
        newApp.setPet(petRepository.findById(appointment.getPet().getId()).get());
        newApp.setVet(vetRepository.findVetById(appointment.getVet().getId()));
        newApp.setDescription(appointment.getDescription());
        newApp.setDate(appointment.getDate());
        appointmentRepository.save(newApp);

        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(newApp.getOwner().getCnp());
        model.addAttribute("ownerAppointments",ownerAppointments);
        return "appointments/appointmentsList";
    }


    @GetMapping(value = "/getpet", produces = "application/json")
    public @ResponseBody ArrayList<Pet> findPet(@RequestParam("ownerCNP") String ownerCNP) {
        ArrayList<Pet> pets = petRepository.findByOwnerCNP(ownerCNP);
        System.out.println("HELLO " + pets);
        return pets;
    }

    @GetMapping(value="/getvets", produces ="application/json")
    public @ResponseBody Set<Vet> findVet(@RequestParam("clinicId") int id){
//        Set<Vet> vets =clinicRepository.findByName(clinicName).getVets();
        Set<Vet> vets = clinicRepository.findById(id).getVets();

        return vets;
    }


}
