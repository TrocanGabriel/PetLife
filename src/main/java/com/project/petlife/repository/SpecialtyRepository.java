package com.project.petlife.repository;


import com.project.petlife.model.Specialty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialtyRepository  extends CrudRepository<Specialty, Integer> {

    @Override
    Optional<Specialty> findById(Integer integer);
}
