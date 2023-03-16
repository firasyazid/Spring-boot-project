package com.example.geekslabo.Controllers.Material;


import com.example.geekslabo.Entities.Material;
import com.example.geekslabo.IServices.IServiceMaterial.IMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Material")
public class MaterialController {
    IMaterialService serv;


    @GetMapping("/list-materials")
    public List<Material> findAllMaterials() {
        return serv.findAllMaterials();
    }

    @PostMapping("/add-material")
    public void createMaterial(@RequestBody Material material) {
        serv.createMaterial(material);
    }


    @PutMapping("update-material/{id}")
    public Material updateMaterial(@PathVariable Integer id, @RequestBody Material materialDetails) {
        return serv.updateMaterial(id, materialDetails);
    }








}
