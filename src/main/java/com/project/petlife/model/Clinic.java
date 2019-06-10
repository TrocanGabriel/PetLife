package com.project.petlife.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clinics")
public class Clinic {

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

    @Column(name = "program_start")
    @Getter
    @Setter
    private int programStart;

    @Column(name = "program_end")
    @Getter
    @Setter
    private int programEnd;

    @OneToMany(mappedBy = "clinic")
    private Set<Vet> vets;


}
