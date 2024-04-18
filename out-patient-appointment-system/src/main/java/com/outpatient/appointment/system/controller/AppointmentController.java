package com.outpatient.appointment.system.controller;
import com.outpatient.appointment.system.dto.AppointmentDTO;
import com.outpatient.appointment.system.entity.Appointment;
import com.outpatient.appointment.system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/available")
    public ResponseEntity<List<AppointmentDTO>> getAvailableAppointments(@RequestParam Long doctorId) {
        LocalDateTime startDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDateTime = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);

        try {
            List<AppointmentDTO> availableAppointments = appointmentService.getAvailableAppointmentsWithDoctorDetails(doctorId, startDateTime, endDateTime);
            return ResponseEntity.ok(availableAppointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody Appointment appointment) {
        try {
            Random random = new Random();
            Long patientId = random.nextLong();
            appointment.setPatientId(patientId);
            appointmentService.bookAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body("createdAppointment");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
