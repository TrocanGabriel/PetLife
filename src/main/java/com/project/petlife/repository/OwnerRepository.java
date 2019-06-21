package com.project.petlife.repository;

import com.project.petlife.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,Integer> {

    @Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    ArrayList<Owner> findByLastName(@Param("lastName") String lastName);

    @Query("SELECT DISTINCT owner from Owner owner where owner.cnp like :ownerCNP%")
    @Transactional
    ArrayList<Owner> findByCNP(@Param("ownerCNP") String ownerCNP);

    Owner getByCnp(String cnp);
}

