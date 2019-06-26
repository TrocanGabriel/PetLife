package com.project.petlife.controller;


import com.project.petlife.model.*;
import com.project.petlife.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private final AppointmentDetailsRepository appointmentDetailsRepository;

    public AppointmentController(AppointmentRepository appointmentRepository, ClinicRepository clinicRepository, PetRepository petRepository, VetRepository vetRepository, OwnerRepository ownerRepository, AppointmentDetailsRepository appointmentDetailsRepository) {
        this.appointmentRepository = appointmentRepository;
        this.clinicRepository = clinicRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
        this.appointmentDetailsRepository = appointmentDetailsRepository;
    }

//    @GetMapping("/search")
//    public String findAppointment(Appointment appointment, Model model) {
//        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerId(ownerRepository.findById());
//
//        model.addAttribute("ownerAppointments",ownerAppointments);
//        return "appointments/appointmentsList";
//    }

    @GetMapping("/findAppointment")
    public String appointments(Model model){
        model.addAttribute("appointment", new Appointment());
        return "appointments/manageAppointments";
    }

//    @GetMapping("/deleteAppointment/{id}")
//    public String deleteAppointment(@PathVariable int id, Model model){
//        Appointment appointment = appointmentRepository.findById(id);
//        appointmentRepository.deleteById(id);
//        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(appointment.getOwner().getCnp());
//        model.addAttribute("ownerAppointments",ownerAppointments);
//        return "appointments/appointmentsList";
//    }

    @GetMapping("/newAppointment")
    public String newAppointment(Model model){
        ArrayList<Clinic> clinics = this.clinicRepository.getAllBy();
        model.addAttribute("clinics", clinics);
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("appointmentDetails",new AppointmentDetails());
        return "appointments/addAppointment";
    }

    @GetMapping("/editAppointment/{id}")
    public String edit(@PathVariable int id, Model model){
        Appointment appointment = appointmentRepository.findById(id);
        ArrayList<Clinic> clinics = this.clinicRepository.getAllBy();
        ArrayList<Pet> pets = this.petRepository.findByOwnerCNP(appointment.getOwnerCnp());
        model.addAttribute("pets", pets);
        model.addAttribute("clinics", clinics);
        model.addAttribute("appointment", appointment);
        return "appointments/editAppointment";

    }

    @PostMapping("/editNewAppointment/{id}")
    public String editAppointment( Model model, @Valid Appointment appointment){
        appointmentRepository.save(appointment);
        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(appointment.getOwnerCnp());
        model.addAttribute("ownerAppointments",ownerAppointments);
        return "appointments/appointmentsList";
    }

    @PostMapping("/createAppointment")
    public String createAppointment(@Valid Appointment appointment,@Valid AppointmentDetails appointmentDetails, Model model){
        appointmentDetails.setAppointment(appointment);
        appointment.setAppointmentDetails(appointmentDetails);
        appointment.setClinic(this.clinicRepository.findById(appointment.getClinic().getId()));
        this.appointmentRepository.save(appointment);
        this.appointmentDetailsRepository.save(appointmentDetails);
        ArrayList<Appointment> ownerAppointments = (ArrayList<Appointment>) this.appointmentRepository.findAll();
        model.addAttribute("ownerAppointments",ownerAppointments);
        model.addAttribute("pets", this.petRepository.findAll());
        model.addAttribute("vets", this.vetRepository.findAll());

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
        Set<Vet> vets = clinicRepository.findById(id).getVets();

        return vets;
    }


}
