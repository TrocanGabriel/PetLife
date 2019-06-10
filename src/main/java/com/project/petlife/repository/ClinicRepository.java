package com.project.petlife.repository;

import com.project.petlife.model.Clinic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {

    @Query("SELECT DISTINCT clinic FROM Clinic clinic")
    @Transactional(readOnly = true)
    Collection<Clinic> findByName(@Param("name") String name);


}
