package com.example.geekslabo.Services.Appointement;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.Repositories.AppointementRepo.ApppointmentRepo;

import java.util.List;

public class AppointmentServiceImpl {

    private ApppointmentRepo appointmentRepo;

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepo.findById(id).orElseThrow(null);
    }

    public Appointment updateAppointment(Integer id, Appointment updatedappointment) {
        Appointment appointment = getAppointmentById(id);
        // Bilan.setName(updatedBilan.getName());

        return appointmentRepo.save(appointment);
    }

    public void deleteAppointment(Integer id) {
        appointmentRepo.deleteById(id);
    }
}


