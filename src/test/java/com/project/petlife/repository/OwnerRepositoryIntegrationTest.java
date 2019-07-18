package com.project.petlife.repository;

import com.project.petlife.model.Clinic;
import com.project.petlife.model.Owner;
import com.project.petlife.repository.OwnerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void whenFindAll_thenReturnClinic() {

        Owner alex = new Owner("popescu", "alex", "0760345142", "alex@gmail.com", "Address", "City", "12331511");
        entityManager.persist(alex);
        entityManager.flush();

        Optional<Owner> found = ownerRepository.findByCNP(alex.getCnp());
        Owner foundOwner = new Owner();
        if (found.isPresent()) {
            foundOwner = found.get();
        }

        assertThat(foundOwner.getCnp())
                .isEqualTo(alex.getCnp());

    }
}