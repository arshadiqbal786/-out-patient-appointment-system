package com.outpatient.appointment.system.repository;

import com.outpatient.appointment.system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}