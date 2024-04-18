package com.outpatient.appointment.system.repository;

import com.outpatient.appointment.system.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);


}