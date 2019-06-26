package com.project.petlife.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="owners")
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")

    public String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "cnp")
    private String cnp;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Pet> pets;

    public Owner() {
    }

    public Owner(String lastName, String firstName, String mobile, String email, String address, String city, String cnp, Set<Pet> pets) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.city = city;
        this.cnp = cnp;
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", cnp='" + cnp + '\'' +
                ", pets=" + pets.size() +
                '}';
    }
}
