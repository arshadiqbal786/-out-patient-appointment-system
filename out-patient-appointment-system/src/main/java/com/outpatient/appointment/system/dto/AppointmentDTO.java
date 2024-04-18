package com.outpatient.appointment.system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class AppointmentDTO {
    private Long id;
    private LocalDateTime appointmentDateTime;
    private Long doctorId;
    private String patientName;
    private String patientAge;
    private DoctorDTO doctor;

    // Getters and setters
}
