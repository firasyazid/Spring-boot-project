package com.example.geekslabo.Services.Appointement;

import com.example.geekslabo.Entities.Bilan;
import com.example.geekslabo.Repositories.AppointementRepo.BilanRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilanServiceImpl {

    public BilanRepo BilanRepository;

    public Bilan createBilan(Bilan bilan) {
        return BilanRepository.save(bilan);
    }

    public List<Bilan> getAllBilans() {
        return BilanRepository.findAll();
    }

    public Bilan getBilanById(Integer id) {
        return BilanRepository.findById(id).orElseThrow(null);
    }

    public Bilan updateBilan(Integer id, Bilan updatedBilan) {
        Bilan bilan = getBilanById(id);
       // Bilan.setName(updatedBilan.getName());

        return BilanRepository.save(bilan);
    }

    public  void deleteBilan(Integer id) {
        BilanRepository.deleteById(id);
    }
}

