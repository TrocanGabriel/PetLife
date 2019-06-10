package com.project.petlife.repository;


import com.project.petlife.model.Specialty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository  extends CrudRepository<Specialty, Integer> {

}
