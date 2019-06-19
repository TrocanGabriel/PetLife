package com.project.petlife.controller;

import com.project.petlife.model.Vet;
import com.project.petlife.repository.VetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("vets", vets);
        return "vets/vetsList";
    }

}
