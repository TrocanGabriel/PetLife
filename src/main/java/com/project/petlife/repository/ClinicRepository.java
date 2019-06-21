package com.project.petlife.repository;

import com.project.petlife.model.Clinic;

import org.springframework.data.repository.CrudRepository;


import java.util.ArrayList;
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {

    Clinic findByName( String name);
    ArrayList<Clinic> getByName(String name);
    Clinic findById( int id);
    ArrayList<Clinic> getById(int id);

    ArrayList<Clinic> getAllBy();

}
