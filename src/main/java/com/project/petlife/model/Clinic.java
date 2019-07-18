package com.project.petlife.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @NotNull
    private String name;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "phone")
    @NotNull
    private String phone;

    @Column(name = "program_start")
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private Timestamp programStart;

    @Column(name = "program_end" )
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private Timestamp programEnd;

    @JsonIgnore
    @OneToMany(mappedBy = "clinic")
    @JsonManagedReference
    private Set<Vet> vets;

    @OneToMany(mappedBy = "clinic")
    @JsonIgnore
    private Set<Appointment> appointments;


    public Clinic(@NotNull String name, @NotNull String address, @NotNull String phone, @NotNull Timestamp programStart, @NotNull Timestamp programEnd) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.programStart = programStart;
        this.programEnd = programEnd;
    }

    public Clinic(String name, String address, String phone, Timestamp programStart, Timestamp programEnd, Set<Vet> vets) {
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
                ", appointments=" + appointments.toString() +
                '}';
    }
}
