package com.example.geekslabo.Services.Material;


import com.example.geekslabo.Entities.Material;
import com.example.geekslabo.IServices.IServiceMaterial.IMaterialService;
import com.example.geekslabo.Repositories.MaterialRepo.MaterialRepository;
import com.example.geekslabo.Repositories.UserRepo.AppUserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialService implements IMaterialService {

    MaterialRepository repom;



    AppUserRepository userRepository;


    @Override
    public List<Material> findAllMaterials() {
        return repom.findAll();
    }

    @Override
    public Material findMaterialById(Integer id) {
        return repom.findById(id).orElse(null);
    }

    @Override
    public Material createMaterial(Material material) {
        return repom.save(material);    }

    @Override
    public Material updateMaterial(Integer id, Material materialDetails) {
        Material oldmaterial = repom.findById(id).orElse(null);
        oldmaterial.setRef(oldmaterial.getRef());
        oldmaterial.setNommateriel(oldmaterial.getNommateriel());
        oldmaterial.setTypemateriel(oldmaterial.getTypemateriel());
        oldmaterial.setStock(oldmaterial.getStock());
        oldmaterial.setPrixunite(oldmaterial.getPrixunite());
        return repom.save(oldmaterial);    }

    @Override
    public void deleteMaterial(Integer id) {
        repom.deleteById(id);


    }




}
