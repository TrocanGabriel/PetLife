package com.project.petlife.repository;

import com.project.petlife.model.AppointmentDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AppointmentDetailsRepository extends CrudRepository<AppointmentDetails,Integer> {

    Optional<AppointmentDetails> findAllByAppointmentId(int id);

    @Query(value = "select * from appointment_details app where MONTH(app.date) = :dateMonth and DAY(app.date)= :dateDay", nativeQuery = true)
    ArrayList<AppointmentDetails> findAllByDate_MonthAndDate_DayOfMonth(int dateMonth, int dateDay);
}
