package com.project.petlife.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pets")
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name ="age")
    private int age;

    @JoinColumn(name = "owner_cnp")
    @ManyToOne(fetch = FetchType.LAZY )
    @JsonBackReference
    private Owner owner;

    public Pet() {
    }

    public Pet(String name, int age, Owner owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", owner=" + owner.getCnp() +
                '}';
    }
}
