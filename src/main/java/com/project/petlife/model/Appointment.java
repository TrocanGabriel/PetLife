package com.project.petlife.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "appointment",cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    private AppointmentDetails appointmentDetails;

    @Column(name = "vet_id")
    private int vetId;

    @Column(name = "pet_id")
    private int petId;

    @Column(name = "owner_cnp")
    private String ownerCnp;

    @JoinColumn(name = "clinic_id")
    @ManyToOne
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
                ", pet=" + petId +
                ", owner=" + ownerCnp +
                ", clinic=" + clinic.getId() +
                '}';
    }

    public Appointment(AppointmentDetails appointmentDetails, int vet_id, int pet_id,String owner_cnp, Clinic clinic) {
        this.appointmentDetails = appointmentDetails;
        this.vetId = vet_id;
        this.petId = pet_id;
        this.ownerCnp = owner_cnp;
        this.clinic = clinic;
    }
}
