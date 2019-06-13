package com.project.petlife.repository;

import com.project.petlife.model.Clinic;
import org.hibernate.loader.collection.CollectionJoinWalker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {

    @Query("SELECT DISTINCT clinic FROM Clinic clinic")
    @Transactional(readOnly = true)
    ArrayList<Clinic> findByName(@Param("name") String name);


    @Query("select distinct clinic from Clinic clinic where clinic.id = ?1")
    @Transactional(readOnly = true)
    ArrayList<Clinic> findById(@Param("id") int id);

}
