package com.project.petlife.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
@Getter
@Setter

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
    private int id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "age")
    @NotNull
    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id")
    @JsonBackReference
    @NotNull
    private  Clinic clinic;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Vet_Specialty",
            joinColumns = {@JoinColumn(name = "vet_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id")}
    )
    @JsonBackReference
    @NotNull
    private Set<Specialty> specialties = new HashSet<>();


    public Vet(String firstName, String lastName, int age, Clinic clinic, Set<Specialty> specialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.clinic = clinic;
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", clinic=" + clinic.getName() +
                ", specialties=" + specialties.size() +
                '}';
    }
}
