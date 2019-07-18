package com.project.petlife.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="owners")
@Getter
@Setter
public class Owner implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")
    @NotNull
    @Size(min=2,max=20)
    private String lastName;

    @Column(name = "first_name")
    @NotNull
    @Size(min=2,max=20)
    private String firstName;

    @Column(name = "mobile")
    @NotNull
    @Size(min=10,max=10)
    private String mobile;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "city")
    @NotNull
    @Size(min=2,max=20)
    private String city;

    @Column(name = "cnp")
    @NotNull
    @Size(min=5,max=13)
    private String cnp;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Pet> pets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonManagedReference
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Appointment> appointmentList;

    public Owner() {
    }

    public Owner(String lastName, String firstName, String mobile, String email, String address, String city, String cnp) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.city = city;
        this.cnp = cnp;
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
