package com.project.petlife.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "specialties")
    private Set<Vet> vets = new HashSet<>();

    public Specialty(String name, String description, Set<Vet> vets) {
        this.name = name;
        this.description = description;
        this.vets = vets;
    }

    public Specialty() {
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vets=" + vets.size() +
                '}';
    }
}
