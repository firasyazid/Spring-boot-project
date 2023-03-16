package com.example.geekslabo.IServices.IServiceAppointement;


import com.example.geekslabo.Entities.*;

import java.util.List;

public interface IServiceAppointment {
    List<Appointment> getAllAppointment();
    Appointment getAppointmentById(Integer id);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Integer id, Appointment appointment);
    void deleteAppointment(Integer id);

}
