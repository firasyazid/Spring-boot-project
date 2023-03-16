package com.example.geekslabo.Services.Material;

import com.example.geekslabo.Entities.Appel;
import com.example.geekslabo.IServices.IServiceMaterial.IAppelService;
import com.example.geekslabo.Repositories.MaterialRepo.AppelRepository;

import java.util.List;

public class AppelService implements IAppelService {

    AppelRepository repoc;
    @Override
    public List<Appel> findAllAppels() {
        return repoc.findAll();
    }

    @Override
    public Appel findAppelById(Integer id) {
        return repoc.findById(id).orElse(null);
    }

    @Override
    public Appel createAppel(Appel appel) {
        return repoc.save(appel);
    }

    @Override
    public Appel updateAppel(Integer id, Appel appelDetails) {
        Appel oldappel = repoc.findById(id).orElse(null);
        oldappel.setQuantity(oldappel.getQuantity());
        oldappel.setDescription(oldappel.getDescription());
        oldappel.setDatedebut(oldappel.getDatedebut());
        oldappel.setDatefin(oldappel.getDatefin());
        return repoc.save(oldappel);
    }

    @Override
    public void deleteAppel(Integer id) {
        repoc.deleteById(id);

    }
}
