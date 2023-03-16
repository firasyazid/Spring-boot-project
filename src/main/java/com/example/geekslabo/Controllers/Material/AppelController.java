package com.example.geekslabo.Controllers.Material;

import com.example.geekslabo.Entities.Appel;
import com.example.geekslabo.IServices.IServiceMaterial.IAppelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Appels")
public class AppelController {
    IAppelService serv ;
    @PostMapping("/add-appel")
    public void createAppel(@RequestBody Appel appel) {
        serv.createAppel(appel);
    }
    @GetMapping("/list-appel")
    public List<Appel> findAllAppels() {
        return serv.findAllAppels();
    }
    @PutMapping("update-appel/{idAppel}")
    public Appel updateAppel(@PathVariable Integer id, @RequestBody Appel appelDetails) {
        return serv.updateAppel(id, appelDetails);
    }
}
