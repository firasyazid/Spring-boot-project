package com.example.geekslabo.IServices.IServiceAppointement;


import com.example.geekslabo.Entities.*;

import java.util.List;

public interface IServiceBilan {
    List<Bilan> getAllBilans();
    Bilan getBilanById(Integer id);
    Bilan createBilan(Bilan bilan);
    Bilan updateBilan(Integer id, Bilan bilan);
    void deleteBilan(Integer id);

}
