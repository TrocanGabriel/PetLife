package com.project.petlife.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clinics")
public class Clinic {

    public Clinic() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Column(name = "phone")
    @Getter
    @Setter
    private String phone;

    @Column(name = "program_start")
    @Getter
    @Setter
    private int programStart;

    @Column(name = "program_end")
    @Getter
    @Setter
    private int programEnd;

    @OneToMany(mappedBy = "clinic")
    @Getter
    @Setter
    private Set<Vet> vets;


    public Clinic(String name, String address, String phone, int programStart, int programEnd, Set<Vet> vets) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.programStart = programStart;
        this.programEnd = programEnd;
        this.vets = vets;
    }
}
