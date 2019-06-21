package com.project.petlife.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "clinics")
@Getter
@Setter
public class Clinic {

    public Clinic() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "program_start")
    private int programStart;

    @Column(name = "program_end")
    private int programEnd;

    @JsonIgnore
    @OneToMany(mappedBy = "clinic")
    @JsonManagedReference
    private Set<Vet> vets;

    @OneToMany(mappedBy = "clinic")
    @JsonIgnore
    private Set<Appointment> appointments;


    public Clinic(String name, String address, String phone, int programStart, int programEnd, Set<Vet> vets) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.programStart = programStart;
        this.programEnd = programEnd;
        this.vets = vets;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", programStart=" + programStart +
                ", programEnd=" + programEnd +
                ", vets=" + vets.size() +
                ", appointments=" + appointments.size() +
                '}';
    }
}
