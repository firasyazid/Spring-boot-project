package com.example.geekslabo.Controllers.AppointementController;


import com.example.geekslabo.Entities.Bilan;
import com.example.geekslabo.Services.Appointement.BilanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Bilan")
public class BilanController {
    @Autowired
    private BilanServiceImpl BilanService;

    @PostMapping
    public Bilan createBilan(@RequestBody Bilan bilan) {
        return BilanService.createBilan(bilan);
    }

    @GetMapping
    public List<Bilan> getAllBilans() {
        return BilanService.getAllBilans();
    }

    @GetMapping("/{id}")
    public Bilan getBilanById(@PathVariable Integer id) {
        return BilanService.getBilanById(id);
    }

    @PutMapping("/{id}")
    public Bilan updateBilan(@PathVariable Integer id, @RequestBody Bilan updatedBilan) {
        return BilanService.updateBilan(id,updatedBilan);
    }

    @DeleteMapping("/{id}")
    public void deleteBilan(@PathVariable Integer id) {
        BilanService.deleteBilan(id);
    }
}


