package com.project.petlife.controller;


import com.project.petlife.model.*;
import com.project.petlife.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
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

    private String listAppointmentsForOwner(Appointment appointment, Model model){
        log.info("Calling method: listAppointmentsForOwner");
        String ownerCNP = appointment.getOwner().getCnp();
        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(ownerCNP);
        model.addAttribute("ownerAppointments",ownerAppointments);
        ArrayList<Pet> pets = this.petRepository.findByOwnerCNP(ownerCNP);
        model.addAttribute("pets", pets);
        Optional<Owner> ownerOptional = this.ownerRepository.findByCNP(ownerCNP);
        if(!ownerOptional.isPresent()) {
            return "error";
        }
        Owner owner = ownerOptional.get();
        model.addAttribute("owner",  owner);
        ArrayList<Vet> vets = this.vetRepository.getAllBy();
        model.addAttribute("vets", vets);
        return "appointments/appointmentsList";
    }

    private String listDataAppointment(Appointment appointment,AppointmentDetails appointmentDetails, Model model, String path){
        log.info("Calling method: listDataAppointment ");

        ArrayList<Clinic> clinicSelected = this.clinicRepository.getById(appointment.getClinic().getId());
        Clinic formClinic = clinicSelected.get(0);
        appointment.setClinic(formClinic);
        Set<Vet> vets = formClinic.getVets();
        model.addAttribute("appointment",appointment);
        model.addAttribute("pets",petRepository.findByOwnerCNP(appointment.getOwner().getCnp()));
        model.addAttribute("vets",vets);
        model.addAttribute("clinics", clinicSelected);
        model.addAttribute("appointmentDetails",appointmentDetails);
        return path;
    }

    @GetMapping("/search")
    public String searchAppointment(@Valid Appointment appointment,BindingResult bindingResult, Model model) {
        log.info("Calling method: searchAppointment ");
        String ownerCNP = appointment.getOwner().getCnp();
        if(!ownerRepository.findByCNP(ownerCNP).isPresent()){
            bindingResult.rejectValue("owner.cnp", "owner.cnp", "This CNP is not valid amongst our pet owners!");
            log.debug("Wrong owner cnp, not found in db: {}", ownerCNP);
            if (bindingResult.hasErrors()) {
                System.out.println("BINDING RESULT ERROR");
                return "appointments/manageAppointments";
            }
        }
        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(ownerCNP);
        model.addAttribute("ownerAppointments",ownerAppointments);
        ArrayList<Pet> pets = this.petRepository.findByOwnerCNP(ownerCNP);
        if(pets.size() == 0){
            bindingResult.rejectValue("owner.cnp", "owner.cnp", "The client with this CNP has no registered pets! Please register in order to continue to manage appointments!");
           log.debug("The owner with cnp: {} has no pets!", ownerCNP);
            if (bindingResult.hasErrors()) {
                log.debug("BINDING RESULT ERROR");
                return "appointments/manageAppointments";
            }
        }
        return  listAppointmentsForOwner(appointment, model );
    }

    @GetMapping("/findAppointment")
    public String appointments(Model model){
        log.info("Calling method: appointments ");
        model.addAttribute("appointment", new Appointment());
        return "appointments/manageAppointments";
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable int id, Model model){
        log.info("Calling method: deleteAppointment ");
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if(!optionalAppointment.isPresent()) {
            log.debug("Appointment id not found in db for delete : {}", id);
            return "error";
        }
        Appointment appointment = optionalAppointment.get();
        this.appointmentRepository.deleteById(id);
        return listAppointmentsForOwner(appointment, model);

    }

    @GetMapping("/newAppointment")
    public String newAppointment(Model model){
        log.info("Calling method: newAppointment ");
        ArrayList<Clinic> clinics = this.clinicRepository.getAllBy();
        model.addAttribute("clinics", clinics);
        model.addAttribute("vets", vetRepository.findAllByClinicId(clinics.get(0).getId()));
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("appointmentDetails",new AppointmentDetails());
        return "appointments/addAppointment";
    }

    @GetMapping("/editAppointment/{id}")
    public String edit(@PathVariable int id, Model model){
        log.info("Calling method: edit ");
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if(!optionalAppointment.isPresent()){
            log.debug("Appointment id not found in db for update: {}", id);
            return "error";
        }
        Appointment appointment = optionalAppointment.get();
        ArrayList<Clinic> clinics = this.clinicRepository.getAllBy();
        ArrayList<Pet> pets = this.petRepository.findByOwnerCNP(appointment.getOwner().getCnp());
        model.addAttribute("pets", pets);
        model.addAttribute("vets", appointment.getClinic().getVets());
        model.addAttribute("clinics", clinics);
        model.addAttribute("appointment", appointment);
        model.addAttribute("appointmentDetails",appointmentDetailsRepository.findAllByAppointmentId(appointment.getId()));
        return "appointments/editAppointment";

    }

    @PostMapping("/editAppointment")
    public String editAppointment( Model model, @Valid Appointment appointment,@Valid AppointmentDetails appointmentDetails, BindingResult bindingResult){
        log.info("Calling method: editAppointment ");

        checkBindings(bindingResult,appointment,appointmentDetails);
        if(!validateDate(appointment,appointmentDetails)){
            bindingResult.rejectValue("date", "date", "Appointments already exists at that hour or it's outside program hours!");
        }
        if (bindingResult.hasErrors()) {
            log.info("BINDING RESULT ERROR");
            return listDataAppointment(appointment, appointmentDetails,model, "appointments/editAppointment");
        }
        appointmentDetails.setAppointment(appointment);
        appointment.setAppointmentDetails(appointmentDetails);
        appointment.setClinic(this.clinicRepository.findById(appointment.getClinic().getId()).get());
        this.appointmentRepository.save(appointment);
        this.appointmentDetailsRepository.save(appointmentDetails);
      return listAppointmentsForOwner(appointment, model );
    }

    private BindingResult checkBindings(BindingResult bindingResult,Appointment appointment, AppointmentDetails appointmentDetails){
        log.info("Entering checkBidings method! ");
        Optional<Pet> pet = petRepository.findById(appointment.getPet().getId());
        Optional<Owner> owner = ownerRepository.findByCNP(appointment.getOwner().getCnp());
        if(!pet.isPresent()){
            bindingResult.rejectValue("pet.id", "pet.id", "The pet does not exists in out database! Please select another");
            log.debug("Errors with specified pet: {}", appointment.getPet().getId());
        } else {
            appointment.setPet(pet.get());
        }

        if(!owner.isPresent()){
            bindingResult.rejectValue("owner.cnp", "owner.cnp", "The pet does not exists in out database! Please select another");
            log.debug("Errors with specified owner: {}", appointment.getOwner().getId());
        } else {
            Owner ownerNew = owner.get();
            appointment.setOwner(ownerNew);

        }
         if ( pet.isPresent() && !pet.get().getOwner().getCnp().equals(appointment.getOwner().getCnp())){
            bindingResult.rejectValue("owner.cnp", "owner.cnp", "The respectiv owner does not have the selected pet");
             bindingResult.rejectValue("pet.id", "pet.id", "The respectiv pet does not have the selected owner!");
             log.debug("Error! The pet and owner does not correspond with each other, owner {} and pet {}", appointment.getOwner().getId(),appointment.getPet().getId() );
         }

        if(!validateDate(appointment,appointmentDetails)){
            bindingResult.rejectValue("date", "date", "Appointments already exists at that hour or it's outside program hours!");
        }
        return bindingResult;
    }
    @PostMapping("/createAppointment")
    public String createAppointment(@Valid Appointment appointment, @Valid AppointmentDetails appointmentDetails, BindingResult bindingResult, Model model){
        log.info("Calling method: createAppointment ");
       checkBindings(bindingResult,appointment,appointmentDetails);
        if (bindingResult.hasErrors()) {
            log.debug("Returning to UI form with error");
            System.out.println("BINDING RESULT ERROR");
                return listDataAppointment(appointment, appointmentDetails,model, "appointments/addAppointment");
        }
        appointmentDetails.setAppointment(appointment);
        appointment.setAppointmentDetails(appointmentDetails);
        Optional<Clinic> clinic = this.clinicRepository.findById(appointment.getClinic().getId());
        if(!clinic.isPresent()){
            log.debug("Specified clinic was not found in db");
            return "error";
        }
        appointment.setClinic(clinic.get());
        this.appointmentRepository.save(appointment);
        this.appointmentDetailsRepository.save(appointmentDetails);
        ArrayList<Appointment> ownerAppointments = this.appointmentRepository.findByOwnerCnp(appointment.getOwner().getCnp());
        model.addAttribute("ownerAppointments",ownerAppointments);
        model.addAttribute("pets", this.petRepository.findAll());
        model.addAttribute("owner", ownerRepository.findByCNP(appointment.getOwner().getCnp()).get());
        model.addAttribute("vets", this.vetRepository.findAll());

        return "appointments/appointmentsList";
}

    private boolean validateDate(Appointment appointment,AppointmentDetails appointmentDetails){
        log.info("Calling method: validateDate ");
        Optional<Clinic> clinicFound = this.clinicRepository.findById(appointment.getClinic().getId());
        if(!clinicFound.isPresent()){
            log.debug("Specified clinic was not found in db");
            return false;
        }
        Clinic clinic = clinicFound.get();
        LocalDateTime appointmentDate = appointmentDetails.getDate();
        if(appointmentDate.isBefore(LocalDateTime.now())){
            log.debug("Specified date of appointment is in the past");
            return false;
        }
        List<AppointmentDetails> appointmentDetailsList = appointmentDetailsRepository.findAllByDate_MonthAndDate_DayOfMonth(appointmentDate.getMonth().getValue(), appointmentDate.getDayOfMonth());
        if(!appointmentDetailsList.isEmpty()){
            appointmentDetailsList = appointmentDetailsList.stream().filter(x -> x.getAppointment().getVetId() == appointment.getVetId()).collect(Collectors.toList());
        }
        if( appointmentDate.getHour() >= clinic.getProgramStart().toLocalDateTime().getHour() && appointmentDate.getHour() <= clinic.getProgramEnd().toLocalDateTime().getHour()){
            List<AppointmentDetails> appSameHour = new ArrayList<>();
            if(appointmentDetailsList.size() !=0){
                LocalDateTime finalAppointmentDate = appointmentDate;
                appSameHour = appointmentDetailsList.stream().filter(x -> x.getDate().getHour() == finalAppointmentDate.getHour()).collect(Collectors.toList());
            }
            if(appSameHour.size() < 2){
                return true;
            }
        }
        return false;
}

    @GetMapping(value = "/getpet", produces = "application/json")
    public @ResponseBody ArrayList<Pet> findPet(@RequestParam("ownerCNP") String ownerCNP) {
        log.info("Calling method: findPet ");
        return petRepository.findByOwnerCNP(ownerCNP);
    }

    @GetMapping(value="/getvets", produces ="application/json")
    public @ResponseBody Set<Vet> findVet(@RequestParam("clinicId") int id){
        log.info("Calling method: findVet ");
        Set<Vet> vets = clinicRepository.findById(id).get().getVets();
        return vets;
    }


}
