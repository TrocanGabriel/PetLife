package com.project.petlife.controller;

import com.project.petlife.model.Vet;
import com.project.petlife.repository.VetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println("SPEC: " + vets.get(0).getSpecialties().toString());
        model.addAttribute("vets", vets);
        return "vets/vetsList";
    }

    @GetMapping(value = "/findVets", produces = "application/json")

    public @ResponseBody ArrayList<Vet> search(Vet vet){
        ArrayList<Vet> foundVets = vetRepository.findVetsByLastName(vet.getLastName());
        System.out.println("FOUND VETS: " + foundVets.size());
        return foundVets;
    }

}
