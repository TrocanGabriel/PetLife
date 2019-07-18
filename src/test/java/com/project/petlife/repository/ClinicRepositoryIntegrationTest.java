package com.project.petlife.repository;


import com.project.petlife.model.Clinic;
import com.project.petlife.model.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClinicRepositoryIntegrationTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClinicRepository clinicRepository;

    @Test
    public void whenFindBy_thenReturnName() throws ParseException {

        Timestamp timestamp = new Timestamp(sdf.parse("09:00").getTime());
        Timestamp timestampEnd = new Timestamp(sdf.parse("18:00").getTime());

        Clinic clinic = new Clinic("name","address","phone",timestamp ,timestampEnd);
//        List<Clinic> clinics = Arrays.asList(clinic);
        entityManager.persist(clinic);
        entityManager.flush();

        ArrayList<Clinic> clinicsNew = clinicRepository.getByName("name");

        assertThat(clinicsNew.get(0).getName())
                .isEqualTo(clinic.getName());
    }
}

