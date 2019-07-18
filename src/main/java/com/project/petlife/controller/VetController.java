package com.project.petlife.controller;

import com.project.petlife.model.Appointment;
import com.project.petlife.model.AppointmentDetails;
import com.project.petlife.model.Clinic;
import com.project.petlife.model.Vet;
import com.project.petlife.repository.VetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("vets")
    public class VetController {

    public VetRepository vetRepository;

    public VetController(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @GetMapping("findVet")
    public String findVet(Model model){
        log.info("Calling method: findVet ");
        ArrayList<Vet> vets = vetRepository.getAllBy();
      model.addAttribute("vet", new Vet());
        model.addAttribute("vets", vets);
        return "vets/vetsList";
    }

    @GetMapping("findVets")
    public String search(Vet vet, Model model){
        log.info("Calling method: search ");
        System.out.println("VET FRONT: " +  vet.getId());
        ArrayList<Vet> foundVets = vetRepository.findAllByLastNameContaining(vet.getLastName());
        System.out.println("VET :" + foundVets.size());
        model.addAttribute("vets",foundVets);
        return "vets/vetsList";
    }

    @GetMapping("appointVet/{id}")
    public String appoint(@PathVariable int id, Model model){
        log.info("Calling method: appoint ");
        Vet vet = vetRepository.findVetById(id);
        ArrayList<Vet> vets = new ArrayList<>();
        vets.add(vet);
        Clinic clinic = vet.getClinic();
        ArrayList<Clinic> clinics = new ArrayList<>();
        clinics.add(clinic);
        model.addAttribute("clinics",clinics);
        model.addAttribute("vets",vets);
        model.addAttribute("appointmentDetails",new AppointmentDetails());
        model.addAttribute("appointment",new Appointment());
        return "appointments/addAppointment";
    }

}
