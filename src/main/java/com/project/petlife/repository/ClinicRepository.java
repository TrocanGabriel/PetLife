package com.project.petlife.repository;

import com.project.petlife.model.Clinic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {

    Clinic findByName( String name);
    ArrayList<Clinic> getByName(String name);
    Optional<Clinic> findById(int id);
    ArrayList<Clinic> getById(int id);

    ArrayList<Clinic> getAllBy();

}
