package com.project.petlife.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "date")
    @Getter
    @Setter
    private Date date;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @JoinColumn(name = "vet_id")
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Vet vet;

    @JoinColumn(name = "pet_id")
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    @JoinColumn(name = "owner_cnp")
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @JoinColumn(name = "clinic_id")
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

}
