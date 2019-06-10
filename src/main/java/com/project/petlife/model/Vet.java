package com.project.petlife.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet {

    public Vet() {
    }

    public Vet(String firstName, String lastName, int age, Clinic clinic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.clinic = clinic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "age")
    @Getter
    @Setter
    private int age;

    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    @Getter
    @Setter
    private  Clinic clinic;

    @ManyToMany(cascade = {CascadeType.ALL})
    @Getter
    @Setter
    @JoinTable(
            name = "Vet_Specialty",
            joinColumns = {@JoinColumn(name = "vet_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id")}
    )
    private Set<Specialty> specialties = new HashSet<>();
}
