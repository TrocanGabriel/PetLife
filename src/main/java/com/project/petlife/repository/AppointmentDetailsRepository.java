package com.project.petlife.repository;

import com.project.petlife.model.AppointmentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentDetailsRepository extends CrudRepository<AppointmentDetails,Integer> {

    Optional<AppointmentDetails> findAllByAppointmentId(int id);
}
