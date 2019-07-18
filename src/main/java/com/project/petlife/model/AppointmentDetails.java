package com.project.petlife.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "appointment_details")
public class AppointmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")

    @NotNull
    private LocalDateTime date;

    @Column(name = "description")
    @NotNull
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id")
    @JsonBackReference
    private Appointment appointment;

    public AppointmentDetails() {
    }

    public AppointmentDetails(LocalDateTime date, String description) {
        this.date = date;
        this.description = description;
    }
}
