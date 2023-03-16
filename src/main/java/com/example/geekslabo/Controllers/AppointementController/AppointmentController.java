package com.example.geekslabo.Controllers.AppointementController;


import com.example.geekslabo.Entities.*;
import com.example.geekslabo.Services.Appointement.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    private AppointmentServiceImpl AppointmentService;
   @PostMapping
   public Appointment createAppointment(@RequestBody Appointment appointment) {
       return AppointmentService.createAppointment(appointment);
   }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return AppointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Integer id) {
        return AppointmentService.getAppointmentById(id);

    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Integer id, @RequestBody Appointment updatedAppointment) {
        return AppointmentService.updateAppointment(id,updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id) {
        AppointmentService.deleteAppointment(id);
    }
}

