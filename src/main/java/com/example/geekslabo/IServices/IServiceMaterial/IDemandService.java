package com.example.geekslabo.IServices.IServiceMaterial;

import com.example.geekslabo.Entities.Demand;

import java.util.List;

public interface IDemandService {
    List<Demand> findAllDemands();
    Demand findDemandById(Integer id);
    Demand createDemand(Demand demand);
    Demand updateDemand(Integer id, Demand demandDetails);
    void deleteDemand(Integer id);
}
