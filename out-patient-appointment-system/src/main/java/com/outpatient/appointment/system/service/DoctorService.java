package com.outpatient.appointment.system.service;

import com.outpatient.appointment.system.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);

    Doctor addDoctor(Doctor doctor);

}
