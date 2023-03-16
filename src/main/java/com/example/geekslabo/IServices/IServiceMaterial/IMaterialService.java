package com.example.geekslabo.IServices.IServiceMaterial;



import com.example.geekslabo.Entities.Material;

import java.util.List;

public interface IMaterialService {

    List<Material> findAllMaterials();
    Material findMaterialById(Integer id);
    Material createMaterial(Material material);
    Material updateMaterial(Integer id, Material materialDetails);
    void deleteMaterial(Integer id);











}
