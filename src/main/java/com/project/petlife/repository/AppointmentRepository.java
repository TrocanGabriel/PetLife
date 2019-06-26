package com.project.petlife.repository;


import com.project.petlife.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {

    ArrayList<Appointment> findByOwnerCnp(String owner_cnp);
    Appointment findById(int id);

    Boolean findFirstById(int id);
}
