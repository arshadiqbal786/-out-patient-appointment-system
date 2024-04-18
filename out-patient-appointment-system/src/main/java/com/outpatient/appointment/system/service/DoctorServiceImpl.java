package com.outpatient.appointment.system.service;

import com.outpatient.appointment.system.entity.Doctor;
import com.outpatient.appointment.system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            return optionalDoctor.get();
        } else {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {

        return doctorRepository.save(doctor);
    }
}

