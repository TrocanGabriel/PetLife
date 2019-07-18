package com.project.petlife.controller;


import com.oracle.tools.packager.Log;
import com.project.petlife.model.*;
import com.project.petlife.repository.ClinicRepository;
import com.project.petlife.repository.OwnerRepository;
import com.project.petlife.repository.PetRepository;
import com.project.petlife.repository.VetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@Slf4j
public class OwnerController {

    public final OwnerRepository ownerRepository;
    public final ClinicRepository clinicRepository;
    public final PetRepository petRepository;
    public final VetRepository vetRepository;
    public OwnerController(OwnerRepository ownerRepository, ClinicRepository clinicRepository, PetRepository petRepository, VetRepository vetRepository) {
        this.ownerRepository = ownerRepository;
        this.clinicRepository = clinicRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
    }

    @GetMapping("owners")
    public String ownersList(Model model) {
        log.info("Calling method: ownersList ");
        ArrayList<Owner> ownerArrayList = (ArrayList<Owner>) ownerRepository.findAll();
        model.addAttribute("owner", new Owner());
        model.addAttribute("owners", ownerArrayList);
        return "owners/ownersList";
    }

    @GetMapping("findOwners")
    public String searchOwner(Model model, Owner owner){
        log.info("Calling method: searchOwner ");
        Optional<Owner> ownersList = ownerRepository.findByCNP(owner.getCnp());
        model.addAttribute("owners",ownersList.get());
        return "owners/ownersList";
    }

    @GetMapping("owners/appointPet/{id}")
    public String appointPet(Model model, @PathVariable int id){
        log.info("Calling method: appointPet id: {} ", id);
        Optional<Pet> pet = petRepository.findById(id);

        if(!pet.isPresent()){
            log.debug("Pet to be created appointment for does not exist! id = {}", id);
            return "error";
        }
        model.addAttribute("pets", pet.get());
        Owner owner = pet.get().getOwner();
        Appointment appointment = new Appointment();
        ArrayList<Clinic> clinics = clinicRepository.getAllBy();
        model.addAttribute("clinics",clinics);
        appointment.setOwner(owner);
        model.addAttribute("vets", vetRepository.findAllByClinicId(clinics.get(0).getId()));
        model.addAttribute("appointment", appointment);
        model.addAttribute("appointmentDetails",new AppointmentDetails());
        return "appointments/addAppointment";
    }

    @DeleteMapping("deleteOwner/{id}")
    public String deleteOwner(@PathVariable int id, Model model){
        log.info("Calling method: delete Owner ");
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if(!ownerOptional.isPresent()){
            log.debug("Owner to be deleted with id = {} not found in db ", id);
            return "error";
        }
        ownerRepository.delete(ownerOptional.get());
        ArrayList<Owner> ownerArrayList = (ArrayList<Owner>) ownerRepository.findAll();
        model.addAttribute("owner", new Owner());
        model.addAttribute("owners", ownerArrayList);
        return "owners/ownersList";
    }

    @GetMapping("checkPets/{id}")
    public String checkPetList(@PathVariable int id, Model model){
        log.info("Calling method: checkPetList ");
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if(!ownerOptional.isPresent()){
            log.debug("Owner with id = {} not found in db ", id);
            return "error";
        }
        Owner owner = ownerOptional.get();
        ArrayList<Pet> pets = petRepository.findByOwnerCNP(owner.getCnp());
        model.addAttribute("owner_id",owner.getId());
        model.addAttribute("pets",pets);
        return "owners/petsList";
    }

    @GetMapping("owners/new")
    public String addNewOwner(Model model){
        log.info("Calling method: addNewOwner ");
        model.addAttribute("owner", new Owner());
        return "owners/addOwner";
    }

    @PostMapping("owners/new")
    public String saveOwner(@ModelAttribute  @Valid Owner owner, BindingResult bindingResult, Model model){
        log.info("Calling method saveOwner");
        String regexStr = "^[0-9]{10}$";
        if (!owner.getMobile().matches(regexStr)) {
            log.debug("Mobile phone of owner is not a valid mobile number: {}", owner.getMobile());
            bindingResult.rejectValue("mobile", "mobile", "Mobile number contains character or doesn't have 10 digits length");
        }
        String regexStrCnp = "^[0-9]{5,13}$";
        if (!owner.getCnp().matches(regexStrCnp)) {
            bindingResult.rejectValue("cnp", "cnp", "CNP contains characters or doesn't have  exact 13 digits length");
        }

        if (bindingResult.hasErrors()) {
            log.info("BINDING RESULT ERROR");
            return "owners/addOwner";
        }
        ownerRepository.save(owner);
        ArrayList<Owner> ownerArrayList = (ArrayList<Owner>) ownerRepository.findAll();
        model.addAttribute("owner", new Owner());
        model.addAttribute("owners", ownerArrayList);
        return "owners/ownersList";
    }

    @GetMapping("owners/newPet/{id}")
    public String newPet (@PathVariable int id, Model model){
        log.info("Calling method: newPet ");
        Pet pet = new Pet();
        Optional<Owner> owner = ownerRepository.findById(id);
        int ownerId;
        if(owner.isPresent()){
             ownerId = owner.get().getId();
        } else {
            return "error";
        }
        model.addAttribute("owner_id",ownerId);
        model.addAttribute("pet",pet);
        return "owners/addPet";
    }

    @DeleteMapping("owners/deletePet/{id}")
    public String deleteAppointment(@PathVariable int id, Model model){
        log.info("Calling method: deletePet ");
        Optional<Pet> petOptional = petRepository.findById(id);
        if(!petOptional.isPresent()) {
            return "error";
        }
            petRepository.deleteById(id);
            Pet pet = petOptional.get();
            Owner owner = pet.getOwner();
        ArrayList<Pet> pets = petRepository.findByOwnerCNP(owner.getCnp());
        model.addAttribute("owner_id",owner.getId());
        model.addAttribute("pets",pets);
        return "owners/petsList";
    }

    @PostMapping("/owners/addPet/{id}")
    public String addPet(@PathVariable int id, @Valid Pet pet, Model model){
        log.info("Calling method: addPet ");
        Optional<Owner> owner = ownerRepository.findById(id);
        Pet newPet = new Pet(pet.getName(),pet.getAge());
        System.out.println("PET ID "  + newPet.getId());
        if(!owner.isPresent()) {
            return "error";
        }
        newPet.setOwner(owner.get());
        petRepository.save(newPet);
        ArrayList<Pet> petsList = petRepository.findByOwnerCNP(owner.get().getCnp());
        model.addAttribute("owner_id",owner.get().getId());
        model.addAttribute("pets",petsList);
        return "owners/petsList";
    }
}
