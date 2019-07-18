package com.project.petlife.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "appointment",cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    @JsonManagedReference
    private AppointmentDetails appointmentDetails;

    @Column(name = "vet_id")
    @NotNull
    private int vetId;

    @JoinColumn(name = "pet_id")
    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pet pet;

    @JoinColumn(name = "owner_cnp", referencedColumnName ="cnp")
    @NotNull
    @JsonBackReference
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne( fetch = FetchType.EAGER)
    private Owner owner;

    @JoinColumn(name = "clinic_id")
    @ManyToOne
    @NotNull
    private Clinic clinic;

    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + appointmentDetails.getDate() +
                ", description='" + appointmentDetails.getDescription() + '\'' +
                ", vet=" + vetId +
                ", pet=" + pet.getName() +
                ", owner=" + owner.getCnp() +
                ", clinic=" + clinic.getId() +
                '}';
    }

    public Appointment(AppointmentDetails appointmentDetails, int vet_id, Pet pet,Owner owner, Clinic clinic) {
        this.appointmentDetails = appointmentDetails;
        this.vetId = vet_id;
        this.pet = pet;
        this.owner = owner;
        this.clinic = clinic;
    }
}
