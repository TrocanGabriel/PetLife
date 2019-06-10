package com.project.petlife.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "mobile")
    @Getter
    @Setter
    private String mobile;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Column(name = "city")
    @Getter
    @Setter
    private String city;

    @OneToMany(mappedBy = "owner")
    @Getter
    @Setter
    private Set<Pet> pets;

}
