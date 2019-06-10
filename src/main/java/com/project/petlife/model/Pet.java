package com.project.petlife.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name ="age")
    @Getter
    @Setter
    private int age;

    @JoinColumn(name = "owner_id", nullable = false)
    @Getter
    @Setter
    @ManyToOne
    private Owner owner;
}
