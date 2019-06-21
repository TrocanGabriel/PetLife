package com.project.petlife.repository;

import com.project.petlife.model.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VetRepository  extends CrudRepository<Vet,Integer> {


    ArrayList<Vet> getAllBy();
    ArrayList<Vet> findVetsByLastName(String lastName);
    Vet findVetById(int id);
}
