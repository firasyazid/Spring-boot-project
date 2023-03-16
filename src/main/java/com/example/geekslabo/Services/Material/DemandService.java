package com.example.geekslabo.Services.Material;

import com.example.geekslabo.Entities.Demand;
import com.example.geekslabo.IServices.IServiceMaterial.IDemandService;
import com.example.geekslabo.Repositories.MaterialRepo.DemandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DemandService implements IDemandService {
    DemandRepository repod;

    @Override
    public List<Demand> findAllDemands() {
        return repod.findAll();
    }

    @Override
    public Demand findDemandById(Integer idDemand) {
        return repod.findById(idDemand).orElse(null);
    }

    @Override
    public Demand createDemand(Demand demand) {
        return repod.save(demand);
    }

    @Override
    public Demand updateDemand(Integer idDemand, Demand demandDetails) {
        Demand olddemand = repod.findById(idDemand).orElse(null);
        olddemand.setPrixunite(olddemand.getPrixunite());
        olddemand.setDatedemand(olddemand.getDatedemand());
        return repod.save(olddemand);    }

    @Override
    public void deleteDemand(Integer idDemand) {
        repod.deleteById(idDemand);

    }


}
