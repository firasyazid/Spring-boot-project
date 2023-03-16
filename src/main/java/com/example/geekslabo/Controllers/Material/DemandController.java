package com.example.geekslabo.Controllers.Material;

import com.example.geekslabo.Entities.Demand;
import com.example.geekslabo.IServices.IServiceMaterial.IDemandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor

@RestController
@RequestMapping("/Demands")
public class DemandController {

    IDemandService serv ;
    @PostMapping("/add-demand")
    public void createDemand(@RequestBody Demand demand) {
        serv.createDemand(demand);
    }
    @GetMapping("/list-demand")
    public List<Demand> findAllDemands() {
        return serv.findAllDemands();
    }
    @PutMapping("update-demand/{id}")
    public Demand updateDemand(@PathVariable Integer id, @RequestBody Demand demandDetails) {
        return serv.updateDemand(id, demandDetails);
    }
}
