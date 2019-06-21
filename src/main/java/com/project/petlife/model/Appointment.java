package com.project.petlife.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "vet_id")
    @OneToOne(cascade = CascadeType.DETACH)
    private Vet vet;

    @JoinColumn(name = "pet_id")
    @OneToOne(cascade = CascadeType.DETACH)
    private Pet pet;

    @JoinColumn(name = "owner_cnp")
    @OneToOne( cascade=CascadeType.DETACH)
    private Owner owner;

    @JoinColumn(name = "clinic_id")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Clinic clinic;

    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", vet=" + vet.getLastName() +
                ", pet=" + pet.getName() +
                ", owner=" + owner.getCnp() +
                ", clinic=" + clinic.getId() +
                '}';
    }

    public Appointment(LocalDateTime date, String description, Vet vet, Pet pet, Owner owner, Clinic clinic) {
        this.date = date;
        this.description = description;
        this.vet = vet;
        this.pet = pet;
        this.owner = owner;
        this.clinic = clinic;
    }
}
