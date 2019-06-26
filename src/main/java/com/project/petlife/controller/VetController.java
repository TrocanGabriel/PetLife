package com.project.petlife.controller;

import com.project.petlife.model.Appointment;
import com.project.petlife.model.Clinic;
import com.project.petlife.model.Vet;
import com.project.petlife.repository.ClinicRepository;
import com.project.petlife.repository.VetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("vets")
    public class VetController {

    public VetRepository vetRepository;

    public VetController(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @GetMapping("findVet")
    public String findVet(Model model){
      ArrayList<Vet> vets = vetRepository.getAllBy();
      model.addAttribute("vet", new Vet());
        model.addAttribute("vets", vets);
        return "vets/vetsList";
    }

    @GetMapping("findVets")
    public String search(Vet vet, Model model){
        System.out.println("VET FRONT: " +  vet.getId());
//        ArrayList<Vet> foundVets = vetRepository.findVByLastNameLike(vet.getLastName());
        ArrayList<Vet> foundVets = vetRepository.findAllByLastNameContaining(vet.getLastName());
        System.out.println("VET :" + foundVets.size());
        model.addAttribute("vets",foundVets);
        return "vets/vetsList";
    }

    @GetMapping("appointVet/{id}")
    public String appoint(@PathVariable int id, Model model){
        Vet vet = vetRepository.findVetById(id);
        ArrayList<Vet> vets = new ArrayList<>();
        vets.add(vet);
        Clinic clinic = vet.getClinic();
        ArrayList<Clinic> clinics = new ArrayList<>();
        clinics.add(clinic);
        model.addAttribute("clinics",clinics);
        model.addAttribute("vets",vets);
        Appointment appointment = new Appointment();
        model.addAttribute("appointment",appointment);
        return "appointments/addAppointment";
    }

}
