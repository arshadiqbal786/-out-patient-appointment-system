package com.outpatient.appointment.system.service;

import com.outpatient.appointment.system.dto.AppointmentDTO;
import com.outpatient.appointment.system.dto.DoctorDTO;
import com.outpatient.appointment.system.entity.Appointment;
import com.outpatient.appointment.system.entity.Doctor;
import com.outpatient.appointment.system.repository.AppointmentRepository;
import com.outpatient.appointment.system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public List<AppointmentDTO> getAvailableAppointmentsWithDoctorDetails(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(doctorId, startDateTime, endDateTime);
        return mapToAppointmentDTO(appointments);
    }

    private List<AppointmentDTO> mapToAppointmentDTO(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentDTO mapToDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setDoctorId(appointment.getDoctorId());
        dto.setPatientName(appointment.getPatientName());
        dto.setPatientAge(appointment.getPatientAge());

        // Fetch doctor details
        Doctor doctor = doctorRepository.findById(appointment.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + appointment.getDoctorId()));

        // Map doctor details to DTO
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSpecialty(doctor.getSpecialty());

        dto.setDoctor(doctorDTO);

        return dto;
    }

    @Override
    public List<Appointment> getAvailableAppointments(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(doctorId, startDateTime, endDateTime);
    }

   @Override
    public void bookAppointment(Appointment appointment) {
       try {
           // You may want to add validation or other business logic here before saving
            appointmentRepository.save(appointment);
        } catch (DataAccessException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to book appointment: " + e.getMessage());
        }
    }
}