package com.outpatient.appointment.system.service;


import com.outpatient.appointment.system.dto.AppointmentDTO;
import com.outpatient.appointment.system.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAvailableAppointments(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);


    void bookAppointment(Appointment appointment);
    List<AppointmentDTO> getAvailableAppointmentsWithDoctorDetails(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
    // Add other relevant methods

