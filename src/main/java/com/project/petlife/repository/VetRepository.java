package com.project.petlife.repository;

import com.project.petlife.model.Vet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VetRepository  extends CrudRepository<Vet,Integer> {


    ArrayList<Vet> getAllBy();
    Vet findVetById(int id);
    @Query(name= "SELECT * FROM VETS  where last_name like CONCAT('%',:lastName,'%')", nativeQuery = true)
    ArrayList<Vet> findVByLastNameLike(String lastName);

    ArrayList<Vet> findAllByLastNameContaining(String lastName);

    ArrayList<Vet> findAllByClinicId(int id);
}
