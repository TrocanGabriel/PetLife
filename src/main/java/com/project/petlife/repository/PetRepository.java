package com.project.petlife.repository;


import com.project.petlife.model.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PetRepository extends CrudRepository<Pet,Integer> {
    @Query(value = "select * from pets pet join owners owner on (pet.owner_id = owner.id) where owner.cnp = :cnp", nativeQuery = true)
    ArrayList<Pet> findByOwnerCNP(String cnp);

    Pet findByNameAndOwnerCnp(String name, String cnp);

    @Override
    Optional<Pet> findById(Integer integer);
}
