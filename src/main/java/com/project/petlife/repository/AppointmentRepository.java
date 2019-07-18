package com.project.petlife.repository;


import com.project.petlife.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {

    ArrayList<Appointment> findByOwnerCnp(String owner_cnp);
    Optional<Appointment> findById(int id);

    void deleteById(int id);

    Boolean findFirstById(int id);
}
