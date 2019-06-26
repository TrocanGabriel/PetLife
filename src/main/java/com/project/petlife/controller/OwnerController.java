package com.project.petlife.controller;


import com.project.petlife.model.Appointment;
import com.project.petlife.model.Clinic;
import com.project.petlife.model.Owner;
import com.project.petlife.model.Pet;
import com.project.petlife.repository.ClinicRepository;
import com.project.petlife.repository.OwnerRepository;
import com.project.petlife.repository.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class OwnerController {

    public final OwnerRepository ownerRepository;
    public final ClinicRepository clinicRepository;
    public final PetRepository petRepository;
    public OwnerController(OwnerRepository ownerRepository, ClinicRepository clinicRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.clinicRepository = clinicRepository;
        this.petRepository = petRepository;
    }

    @GetMapping("owners")
    public String ownersList(Model model) {
        ArrayList<Owner> ownerArrayList = (ArrayList<Owner>) ownerRepository.findAll();
        model.addAttribute("owner", new Owner());
        model.addAttribute("owners", ownerArrayList);
        return "owners/ownersList";
    }

    @GetMapping("findOwners")
    public String searchOwner(Model model, Owner owner){
        Optional<Owner> ownersList = ownerRepository.findByCNP(owner.getCnp());
        model.addAttribute("owners",ownersList.get());
        return "owners/ownersList";
    }

    @GetMapping("appointPet/{id}")
    public String appointPet(Model model, @PathVariable int id){
        Optional<Pet> pet = petRepository.findById(id);
        model.addAttribute("pets", pet.get());
        Owner owner = pet.get().getOwner();
        Appointment appointment = new Appointment();
        ArrayList<Clinic> clinics = clinicRepository.getAllBy();
        model.addAttribute("clinics",clinics);
        appointment.setOwnerCnp(owner.getCnp());
        model.addAttribute("appointment", appointment);
        return "appointments/addAppointment";
    }

    @GetMapping("checkPets/{id}")
    public String checkPetList(@PathVariable int id, Model model){
        Optional<Owner> owner = ownerRepository.findById(id);
        ArrayList<Pet> pets = petRepository.findByOwnerCNP(owner.get().getCnp());
        model.addAttribute("owner_id",owner.get().getId());
        model.addAttribute("pets",pets);
        return "owners/petsList";
    }

    @GetMapping("owners/new")
    public String addNewOwner(Model model){
        model.addAttribute("owner", new Owner());
        return "owners/addOwner";
    }

    @PostMapping("owners/addOwner")
    public String saveOwner(Owner owner,Model model){
        ownerRepository.save(owner);
        ArrayList<Owner> ownerArrayList = (ArrayList<Owner>) ownerRepository.findAll();
        model.addAttribute("owner", new Owner());
        model.addAttribute("owners", ownerArrayList);
        return "owners/ownersList";
    }

    @GetMapping("owners/newPet/{id}")
    public String newPet (@PathVariable int id, Model model){
        Pet pet = new Pet();
        int ownerId = petRepository.findById(id).get().getOwner().getId();
//        model.addAttribute("ownerId",ownerId);
        model.addAttribute("owner_id",ownerId);
        model.addAttribute("pet",pet);
        return "owners/addPet";
    }

    @GetMapping("owners/addPet/{id}")
    public String addPet(@PathVariable int id,Pet pet, Model model){
        Optional<Owner> owner = ownerRepository.findById(id);
        System.out.println("PET ID "  + pet.getId());
        pet.setOwner(owner.get());
        petRepository.save(pet);
        ArrayList<Pet> petsList = petRepository.findByOwnerCNP(owner.get().getCnp());
        model.addAttribute("owner_id",owner.get().getId());
        model.addAttribute("pets",petsList);
        return "owners/petsList";
    }
}
